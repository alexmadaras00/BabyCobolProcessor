package parse

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import java.io.File

/**
 * Reads a file, preprocesses it and returns a CharStream from it.
 */
fun readFile(fileName: String): CharStream {
    val file = File(fileName)
    val rawCode = file.readText()
    return preprocess(rawCode)
}

/**
 * Any preprocessing to be done on the code.
 */
fun preprocess(code: String): CharStream {
    // For now, no preprocessing. Just turning it into a CharStream for ANTLR.
    return CharStreams.fromString(code)
}

