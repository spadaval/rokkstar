package net.ascheja.rokkstar.interpreter

import net.ascheja.rokkstar.ast.FunctionCallExpression
import net.ascheja.rokkstar.typesystem.Value
import net.ascheja.rokkstar.typesystem.values.UndefinedValue

fun RockstarVM.runFunction(expression: FunctionCallExpression): Value = this.runWithStackContext {
    val declaration = this.getFunction(expression.identifier)
    val arguments = expression.arguments.map { evaluate(it) }
    declaration.parameters.forEachIndexed { index, name ->
        this[name] = if (index < arguments.size) arguments[index] else UndefinedValue
    }
    when (val result = this.runStatement(declaration.body)) {
        is Result.Return -> result.value
        else -> UndefinedValue
    }
}
