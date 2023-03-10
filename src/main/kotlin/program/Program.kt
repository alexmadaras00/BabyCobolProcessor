package program

/**
 * A Paragraph has a name and a list of Sentences.
 */
typealias Paragraph = Pair<String, List<Sentence>>

/**
 * Mutable version of Paragraph, used in building the program.
 */
typealias MutableParagraph = Pair<String, MutableList<MutableSentence>>

/**
 * A Sentence is a list of Statements.
 */
typealias Sentence = List<Statement>

/**
 * Mutable version of Sentence, used in building the program.
 */
typealias MutableSentence = MutableList<Statement>

/**
 * An Instruction takes a state and returns the transformed state.
 */
typealias Statement = (State) -> State

/**
 * The first value refers to the paragraph index,
 * the second value to the sentence index,
 * the third value to the statement index.
 */
typealias ProgramIndex = Triple<Int, Int, Int>

/**
 * A BabyCOBOL Program.
 */
data class BabyCobol(
    val identification: Map<String, String>,
    val data: Map<String, BabyCobolObject> = emptyMap(),
    val procedure: List<Paragraph>
) {
    /**
     * Runs the program.
     */
    fun run() {
        var state = State(data.toMutableMap(), procedure)
        // Move to first paragraph, first sentence, first statement
        state.programCounter.push(ProgramIndex(0, 0, 0))
        while (!state.stop) {
            val (par, sent, stat) = state.programCounter.peek()
            val instr = procedure[par].second[sent][stat]
            // instr is assumed to update the programCounter. To simply proceed, invoke state.next()
            //It transforms the initial state into  the next one (??)
            state = instr(state)
        }
    }
}