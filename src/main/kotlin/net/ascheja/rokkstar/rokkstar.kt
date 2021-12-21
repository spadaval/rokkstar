package net.ascheja.rokkstar

import net.ascheja.rokkstar.ast.Program
import net.ascheja.rokkstar.interpreter.RockstarVM
import net.ascheja.rokkstar.interpreter.runProgram
import net.ascheja.rokkstar.parser.Lexer
import net.ascheja.rokkstar.parser.StatementParser
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 2) {
        displayHelp()
    }
    when (args[0]) {
        "run" -> runProgram(parseProgram(File(args[1])))
        else -> displayHelp()
    }
}

fun displayHelp(): Nothing {
    println("rokkstar run <file>         run the provided file with the rockstar interpreter")
    exitProcess(1)
}

fun parseProgram(file: File): Program = parseProgram(file.readText())

fun parseProgram(content: String): Program {
    return StatementParser().parseProgram(Lexer(content).toTokenSource())
}

fun runProgram(program: Program) {
    RockstarVM().runProgram(program)
}