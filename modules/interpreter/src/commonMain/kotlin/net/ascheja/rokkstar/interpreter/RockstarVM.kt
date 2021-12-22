package net.ascheja.rokkstar.interpreter

import net.ascheja.rokkstar.ast.*
import net.ascheja.rokkstar.typesystem.Value

private val UNDEFINED_FUNCTION = FunctionDeclaration(
    Identifier(""),
    listOf(),
    BlockStatement(listOf(ReturnStatement(UndefinedConstant)))
)

// scopes are variable-only for now, first-class function support not provided yet
data class StackFrame(
    private val variables: MutableMap<Identifier, Value> = mutableMapOf(),
) {
    operator fun set(name: Identifier, value: Value) {
        variables[name] = value
    }

    operator fun get(name: Identifier): Value? =
        variables[name]
}

// data model for the Rockstar Virtual Machine
data class RockstarVM(
    //functions are global
    private val functionDeclarations: MutableMap<Identifier, FunctionDeclaration> = mutableMapOf(),
    private val stack: MutableList<StackFrame> = mutableListOf(StackFrame()),
    val outputFn: (Value) -> Unit = ::println
) {

    private val topOfStack
        get() = stack[stack.size - 1]

    operator fun set(name: Identifier, declaration: FunctionDeclaration) {
        functionDeclarations[name] = declaration
    }

    operator fun set(name: Identifier, value: Value) {
        topOfStack[name] = value
    }

    fun getFunction(name: Identifier): FunctionDeclaration =
        functionDeclarations[name] ?: UNDEFINED_FUNCTION

    fun getVariable(name: Identifier): Value =
        stack.reversed().firstNotNullOf { it[name] }

    private fun push() {
        stack.add(StackFrame())
    }

    private fun pop() {
        stack.removeAt(stack.size - 1)
    }

    fun <T> runWithStackContext(lambda: () -> T): T {
        push()
        return lambda.invoke().also { pop() }
    }
}

fun RockstarVM.runProgram(program: Program): Result =
    runStatement(program.root)
