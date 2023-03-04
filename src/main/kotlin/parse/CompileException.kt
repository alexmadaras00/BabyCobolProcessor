package parse

data class CompileException(val errors: List<String>): Exception() {
    override fun getLocalizedMessage(): String {
        return errors.joinToString("\n", "Compilation failed: \n")
    }

    companion object {
        /**
         * Formats a compilation error.
         * @param line the line in which the error was found
         * @param col the column in which the error was found
         * @param message the error message
         */
        fun MutableList<String>.addError(line: Int, col: Int, message: String) {
            this.add("Error at line $line:$col $message")
        }
    }
}
