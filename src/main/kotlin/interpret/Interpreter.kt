package interpret

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import parse.CobolBaseListener
import parse.CobolParser
import parse.CobolParser.AtomicMoveContext
import parse.CobolParser.IdentifierAtomContext
import parse.CobolParser.LiteralAtomContext
import parse.CobolParser.NonnumericLitContext
import parse.CobolParser.NumericLitContext
import parse.CobolParser.PrimitiveIdContext
import parse.CompileException
import parse.CompileException.Companion.addError
import program.*


class Interpreter(): CobolBaseListener() {
    val identification = emptyMap<String, String>().toMutableMap()
    val symbolTable = emptyMap<String, Value>().toMutableMap()
    val procedure = listOf(MutableParagraph("", emptyList<MutableSentence>().toMutableList())).toMutableList()
    val errors = emptyList<String>().toMutableList()

    /**
     * Interprets an AST to convert it into a BabyCOBOL Program.
     */
    fun interpret(tree: ParseTree): BabyCobol {
        ParseTreeWalker().walk(this, tree)

        // Throw a CompileException if errors were encountered, or return the resulting BabyCOBOL program.
        if (errors.isNotEmpty()) throw CompileException(errors)
        return BabyCobol(identification.toMap(), symbolTable.toMap(), procedure.toList())
    }

    // --- Bookkeeping section ----------------------------------------------------------------------------------------
    /**
     * Adds an error at a given node.
     * @param node the node where the error was encountered
     * @param message the error message
     */
    private fun addError(node: ParserRuleContext, message: String) {
        addError(node.start, message)
    }

    /**
     * Same as above, but adds an error at a token.
     * @param token the token where the error was encountered
     * @param message the error message
     */
    private fun addError(token: Token, message: String) {
        errors.addError(token.line, token.charPositionInLine, message)
    }

    /**
     * Adds a statement to the current position in the procedure IR.
     */
    private fun addStatement(stat: (State) -> State) {
        val curProcedure = procedure[procedure.lastIndex]
        val curSentence = curProcedure.second.let { it[it.lastIndex] }
        curSentence.add(stat)
    }

    // --- Scope enters and exits -------------------------------------------------------------------------------------
    /**
     * Adds every entry in the Identification Division to the program.
     */
    override fun exitId_div(ctx: CobolParser.Id_divContext) {
        // PROGRAM-ID is mandatory, add error if it is missing
        if (ctx.ID_CLAUSE().none { it.text.takeWhile { it != '.' } == "PROGRAM-ID" }) {
            addError(ctx, "Missing mandatory clause PROGRAM-ID")
        }
        // Take all name-value pairs and add them to the list
        identification.putAll(ctx.ID_CLAUSE().associate {
            it.text.split(". ")
                .let { (name, value) -> name to value }
        })
    }

    /**
     * Opens a new paragraph. Preceding paragraphs should now be considered final.
     */
    override fun enterParagraph(ctx: CobolParser.ParagraphContext) {
        val paragraph = ctx.procedure_name().text
        procedure.add(MutableParagraph(paragraph, emptyList<MutableSentence>().toMutableList()))
    }

    /**
     * Opens a new sentence. Preceding sentences should now be considered final.
     */
    override fun enterSentence(ctx: CobolParser.SentenceContext) {
        procedure[procedure.lastIndex].second.add(emptyList<Statement>().toMutableList())
    }

    // --- Statements -------------------------------------------------------------------------------------------------

    override fun exitAddStat(ctx: CobolParser.AddStatContext) {
        val valueLeft = when (ctx.atomic(0)) {
            is NumericContext -> Value.Numeric((ctx.atomic() as NumericContext).NUMERIC().text)
            is NonnumericContext -> Value.NonNumeric((ctx.atomic() as NonnumericContext).NONNUMERIC().text)
            is IdentifierContext -> symbolTable[(ctx.atomic() as IdentifierContext).COBOL_WORD().text]
            else -> TODO()
        }
        val valueRight = when (ctx.atomic(1)) {
            is NumericContext -> Value.Numeric((ctx.atomic() as NumericContext).NUMERIC().text)
            is NonnumericContext -> Value.NonNumeric((ctx.atomic() as NonnumericContext).NONNUMERIC().text)
            is IdentifierContext -> symbolTable[(ctx.atomic() as IdentifierContext).COBOL_WORD().text]
            else -> TODO()
        }
        if (ctx.GIVING().size > 0) {
        
        } else {
            instructions.add { state ->
                state.apply {
                    ctx.atomic()?.forEach { it ->
                        when (it) {
                            is NumericContext -> {
                                val targets = ctx.atomic().map { ctx.atomic(1).text }
                                targets.forEach {
                                    variables[it] =
                                        Value.Numeric(
                                            valueLeft.toString().toDouble() + valueRight.toString().toDouble()
                                        )
                                }
                            }
                            is NonnumericContext -> TODO()
                            is IdentifierContext -> TODO()
                        }
                    }
                }
            }
        }
    }

    override fun exitDisplayStat(ctx: CobolParser.DisplayStatContext) {
        val wna = ctx.wna() != null

        val toDisplay: List<(State) -> String> = ctx.display_clause().map { when (val atom = it.atomic()) {
            is LiteralAtomContext -> when (atom.literal()) {
                is NumericLitContext -> { _ -> atom.text }
                is NonnumericLitContext -> { _ -> atom.text }
                else -> TODO("This should not occur unless the grammar rule 'literalAtom' was changed")
            }
            is IdentifierAtomContext -> {state -> state.data[it.atomic().text]!!.toString() }
            else -> TODO("This should not occur unless the grammar rule 'atomic' was changed")
        }  }

        addStatement { state -> state.apply {
            val toPrint = toDisplay.map { it(state) } // toDisplay has functions, so invoke with the State
            if (wna) {
                toPrint.forEach { print(it) }
            } else {
                toPrint.forEach { println(it) }
            }
            this.next()
        } }
    }

    override fun exitMoveStat(ctx: CobolParser.MoveStatContext) {
        val value = when (val move = ctx.move_expression()) {
            is AtomicMoveContext -> {
                when (val atom = move.atomic()) {
                is LiteralAtomContext -> Value(atom.literal().text)
                // Not entirely correct as it does not check what kind of identifier it is. Array access and qualification will break this
                is IdentifierAtomContext -> symbolTable[atom.identifier().text]!!
                else -> TODO("This should not occur unless the grammar rule 'atomicMove' was changed")
                }
            }
            else -> TODO()
        }

        val targets = ctx.to.map {
            when (it) {
                is PrimitiveIdContext -> it.COBOL_WORD().text
                else -> TODO("This should not occur unless the grammar rule 'identifier' was changed")
            }
        }
        addStatement {state -> state.apply {
            targets.forEach { target -> data[target] = value }
            this.next()
        } }
    }

    // --- Control Flow section ---------------------------------------------------------------------------------------
    override fun exitNextStat(ctx: CobolParser.NextStatContext?) {
        addStatement {  state -> state.apply {
            this.nextSentence()
        } }
    }

    override fun exitStopStat(ctx: CobolParser.StopStatContext?) {
        addStatement { state -> state.apply {
            this.stop()
        } }
    }
}