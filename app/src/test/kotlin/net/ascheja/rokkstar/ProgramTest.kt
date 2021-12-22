package net.ascheja.rokkstar

import net.ascheja.rokkstar.interpreter.RockstarVM
import net.ascheja.rokkstar.interpreter.runProgram
import net.ascheja.rokkstar.parser.Lexer
import net.ascheja.rokkstar.parser.StatementParser
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class ProgramTest {

    @Test
    fun `easy fizz-buzz executed correctly`() {
        val content = readFile("fizzbuzz-easy.rock")
        val expectedOutput = readFile("fizzbuzz.rock.out")
        assertEquals(expectedOutput, execute(content))
    }

    @Test
    fun `hard fizz-buzz executed correctly`() {
        val content = readFile("fizzbuzz-hard.rock")
        val expectedOutput = readFile("fizzbuzz.rock.out")
        assertEquals(expectedOutput, execute(content))
    }

    @Test
    fun `99 bottles of beer executed correctly`() {
        val content = readFile("99-bottles-of-beer.rock")
        val expectedOutput = readFile("99-bottles-of-beer.rock.out")
        assertEquals(expectedOutput, execute(content))
    }

    @Test
    fun `fibonacci executed correctly`() {
        val content = readFile("fibonacci.rock")
        val expectedOutput = readFile("fibonacci.rock.out")
        assertEquals(expectedOutput, execute(content))
    }

    @Test
    fun `nested function scopes`() {
        val content = readFile("nested_function_scopes.rock")
        val expectedOutput = readFile("nested_function_scopes.rock.out")
        assertEquals(expectedOutput, execute(content))
    }

    @Test
    fun `nested functions`() {
        val content = readFile("nested_functions.rock")
        val expectedOutput = readFile("nested_functions.rock.out")
        assertEquals(expectedOutput, execute(content))
    }

    private fun readFile(filename: String): String {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        return File(javaClass.classLoader.getResource(filename).toURI()).readText()
    }

    private fun execute(content: String): String {
        val program = StatementParser().parseProgram(Lexer(content).toTokenSource())
        val output = StringBuilder()
        val rockstarVM = RockstarVM(outputFn = { output.appendLine(it) })
        rockstarVM.runProgram(program)
        return output.toString()
    }
}