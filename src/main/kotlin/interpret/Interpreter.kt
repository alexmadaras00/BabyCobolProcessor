package interpret
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import parse.CobolBaseListener
import parse.CobolParser
import parse.CobolParser.IdentifierContext
import parse.CobolParser.NonnumericContext
import parse.CobolParser.NumericContext
import parse.CompileException


class Interpreter(): CobolBaseListener() {
    val symbolTable = emptyMap<String, Value?>().toMutableMap()
    val instructions = emptyList<(State) -> State>().toMutableList()
    val errors = emptyList<String>().toMutableList()

    /**
     * Interprets an AST to convert it into a BabyCOBOL Program.
     */
    fun interpret(tree: ParseTree): BabyCobol {
        ParseTreeWalker().walk(this, tree)

        // Throw a CompileException if errors were encountered, or return the resulting BabyCOBOL program.
        if (errors.isNotEmpty()) {
            throw CompileException(errors)
        }
        return BabyCobol(symbolTable, instructions)
    }

    /**
     * Adds an error at a given node.
     * @param node the node where the error was encountered
     * @param message the error message
     */
    fun addError(node: ParserRuleContext, message: String) {
        addError(node.start, message)
    }

    /**
     * Same as above, but adds an error at a token.
     * @param token the token where the error was encountered
     * @param message the error message
     */
    fun addError(token: Token, message: String) {
        errors.add("Error at line ${token.line}:${token.charPositionInLine} $message")
    }

    override fun exitWs_sentence(ctx: CobolParser.Ws_sentenceContext) {
        symbolTable[ctx.COBOL_WORD().text!!] = null
    }

    override fun exitDisplayStat(ctx: CobolParser.DisplayStatContext) {
        if (ctx.WITH_NO_ADVANCING() != null) {
            instructions.add { state -> state.also { ctx.atomic()?.forEach { when (it) {
                is NumericContext -> print(it.NUMERIC().text)
                is NonnumericContext -> print(it.NONNUMERIC().text)
                is IdentifierContext -> print(state.variables[it.COBOL_WORD().text]!!.getString())
            }  } } }
        } else {
            instructions.add { state -> state.also { ctx.atomic()?.forEach { when (it) {
                is NumericContext -> println(it.NUMERIC().text)
                is NonnumericContext -> println(it.NONNUMERIC().text)
                is IdentifierContext -> println(state.variables[it.COBOL_WORD().text]!!.getString())
            }  } } }
        }
    }

    override fun exitMoveStat(ctx: CobolParser.MoveStatContext) {
        val value = when (ctx.atomic()) {
            is NumericContext -> Value.Numeric((ctx.atomic() as NumericContext).NUMERIC().text)
            is NonnumericContext -> Value.NonNumeric((ctx.atomic() as NonnumericContext).NONNUMERIC().text)
            is IdentifierContext -> symbolTable[(ctx.atomic() as IdentifierContext).COBOL_WORD().text]
            else -> TODO("If you see this, a branch is missing here")
        }
        instructions.add { state -> state.apply {
                val targets = ctx.COBOL_WORD().map { it.text }
                targets.forEach { variables[it] = value }
            }
        }
    }

    override fun exitStop_statement(ctx: CobolParser.Stop_statementContext) {
        instructions.add { state -> state.apply { stop = true } }
    }


}