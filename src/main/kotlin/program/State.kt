package program

import java.util.*

/**
 * State of a BabyCOBOL program.
 * @param data all variables with their values
 * @param procedure all statements of the program
 * @param programCounter pointer to the next statement to be executed
 * @param stop: stop flag
 */
data class State(
    val data: MutableMap<String, BabyCobolObject>,
    val procedure: List<Paragraph>,
    val programCounter: Stack<ProgramIndex> = Stack<ProgramIndex>(),
    var stop: Boolean = false
) {
    /**
     * Advances the programCounter to the next statement.
     * If the last statement of the sentence was reached, it goes to the next sentence.
     */
    fun next() {
        val (par, sent, stat) = programCounter.peek()
        if (stat+ 1 < procedure[par].second[sent].size) {
            programCounter[programCounter.lastIndex] = ProgramIndex(par, sent, stat + 1)
        } else {
            nextSentence()
        }
    }

    /**
     * Advances the programCounter to the next Sentence.
     * If the last sentence was reached, it sets stop to true.
     */
    fun nextSentence() {
        val (par, sent, _) = programCounter.pop()
        // End of paragraph not reached, so advance
        if (sent + 1 < procedure[par].second.size) {
            programCounter.push(ProgramIndex(par, sent + 1, 0))
            // End of paragraph reached, return if we were called
        } else if (programCounter.size > 0) {
            return
            // Not called, next paragraph
        } else if (par + 1 < procedure.size) {
            programCounter.push(ProgramIndex(par + 1, 0, 0))
            // Last paragraph, so stop
        } else {
            stop = true
        }
    }

    fun call(paragraph: String) {
        val par = procedure.indexOfFirst { it.first == paragraph }
        if (par == -1) error("Paragraph $paragraph does not exist")
        programCounter.push(ProgramIndex(par, 0, 0))
    }

    /**
     * Stops the program.
     */
    fun stop() { stop = true }
}