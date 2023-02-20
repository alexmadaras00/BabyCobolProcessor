package interpret

/**
 * A Symbol Table is a map from identifiers to values. If the variable has not been assigned a value, null is returned.
 * It has to be mutable because its entries can change during elaboration and run phases.
 */
typealias SymbolTable = MutableMap<String, Value?>
/**
 * An Instruction takes a state and returns the transformed state.
 */
typealias Instruction = (State) -> State

/**
 * Value class, used for data of different types.
 * All implementations should also have a constructor that accepts a string.
 */
sealed interface Value {
    // I just added String and Double, but apparently BabyCOBOL types are quite different.
    /**
     * Returns a String representation of the value.
     */
    fun getString(): String

    data class NonNumeric(val data: String): Value {
        // Secondary constructor is implicit here as it is literally a string

        override fun getString() = data
    }

    data class Numeric(val data: Double): Value {
        constructor(text: String): this(text.toDouble())

        override fun getString() = data.toString()
    }
}

/**
 * State of a BabyCOBOL program.
 * @param variables
 */
data class State(val variables: SymbolTable, var programCounter: Int = 0, var stop: Boolean = false)

/**
 * A BabyCOBOL Program.
 */
data class BabyCobol(
    val symbolTable: SymbolTable = emptyMap<String, Value?>().toMutableMap(),
    val instructions: MutableList<Instruction> = emptyList<Instruction>().toMutableList(),
) {
    /**
     * Runs the program.
     */
    fun run() {
        var state = State(symbolTable)
        while (!state.stop) {
            state = instructions[state.programCounter](state)
            state.programCounter++
        }
    }
}