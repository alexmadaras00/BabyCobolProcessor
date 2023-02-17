package interpret
import parse.CobolBaseListener
import parse.CobolParser
import parse.CobolParser.IdentifierContext
import parse.CobolParser.NonnumericContext
import parse.CobolParser.NumericContext

sealed class Value {
    data class NonNumeric(val data: String): Value()
    data class Numeric(val data: Double): Value()
}

data class State(val variables: MutableMap<String, Value?>, var programCounter: Int = 0, var stop: Boolean = false)

class Interpreter(): CobolBaseListener() {
    val symbolTable = emptyMap<String, Value?>().toMutableMap();
    val instructions = emptyList<(State) -> State>().toMutableList()

    override fun exitProgram(ctx: CobolParser.ProgramContext?) {
        // Execute it!
        var state = State(symbolTable)
        while (!state.stop) {
            state = instructions[state.programCounter](state)
        }
    }

    override fun exitWs_sentence(ctx: CobolParser.Ws_sentenceContext?) {
        symbolTable[ctx!!.COBOL_WORD()?.text!!] = null
    }

    override fun exitDisplayStat(ctx: CobolParser.DisplayStatContext?) {
        if (ctx!!.WITH_NO_ADVANCING() != null) {
            instructions.add { state -> state.also { ctx.atomic()?.forEach { when (it) {
                is NumericContext -> print(it.NUMERIC().text)
                is NonnumericContext -> print(it.NONNUMERIC().text)
                is IdentifierContext -> print(state.variables[it.COBOL_WORD().text])
            }  } } }
        } else {
            instructions.add { state -> state.also { ctx.atomic()?.forEach { when (it) {
                is NumericContext -> println(it.NUMERIC().text)
                is NonnumericContext -> println(it.NONNUMERIC().text)
                is IdentifierContext -> println(state.variables[it.COBOL_WORD().text])
            }  } } }
        }
    }
}