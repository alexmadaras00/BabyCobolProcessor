package parse

data class CompileException(val errors: List<String>): Exception() {
    override fun getLocalizedMessage(): String {
        return errors.joinToString("\n", "Compilation failed: \n")
    }
}
