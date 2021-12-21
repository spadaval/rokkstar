package net.ascheja.rokkstar.interpreter

import net.ascheja.rokkstar.ast.*
import net.ascheja.rokkstar.typesystem.Value
import net.ascheja.rokkstar.typesystem.values.*

internal fun RockstarVM.evaluateBoolean(expression: Expression) =
    this.evaluate(expression).toBoolean()

fun RockstarVM.evaluate(expression: Expression): Value = when (expression) {
    is Value -> expression
    is Constant -> expression.toValue()
    is VariableLookup -> this.getVariable(expression.identifier)
    is BinaryOperatorExpression -> this.evaluateBinaryExpression(expression)
    is UnaryOperatorExpression -> this.evaluateUnaryExpression(expression)
    is FunctionCallExpression -> this.runFunction(expression)
}

fun Constant.toValue(): Value = when (this) {
        is NullConstant -> NullValue
        is UndefinedConstant -> UndefinedValue
        is NumberConstant -> NumberValue(value)
        is BooleanConstant -> BooleanValue(value)
        is StringConstant -> StringValue(value)
    }

fun RockstarVM.evaluateBinaryExpression(expression: BinaryOperatorExpression): Value {
    val left = evaluate(expression.left)
    val right = evaluate(expression.right)
    return when (expression.operator) {
        BinaryOperatorExpression.Operator.MULTIPLY -> left * right
        BinaryOperatorExpression.Operator.DIVIDE -> left / right
        BinaryOperatorExpression.Operator.ADD -> left + right
        BinaryOperatorExpression.Operator.SUBTRACT -> left - right
        BinaryOperatorExpression.Operator.EQUALS -> BooleanValue(left == right)
        BinaryOperatorExpression.Operator.NOT_EQUALS -> BooleanValue(left != right)
        BinaryOperatorExpression.Operator.GREATER -> BooleanValue(left > right)
        BinaryOperatorExpression.Operator.LESS -> BooleanValue(left < right)
        BinaryOperatorExpression.Operator.GREATER_EQUALS -> BooleanValue(left >= right)
        BinaryOperatorExpression.Operator.LESS_EQUALS -> BooleanValue(left <= right)
        BinaryOperatorExpression.Operator.AND -> BooleanValue(left.toBoolean() && right.toBoolean())
        BinaryOperatorExpression.Operator.OR -> BooleanValue(left.toBoolean() || right.toBoolean())
        BinaryOperatorExpression.Operator.NOR -> BooleanValue(!left.toBoolean() && !right.toBoolean())
    }
}

fun RockstarVM.evaluateUnaryExpression(expression: UnaryOperatorExpression): Value {
    val subject = expression.expression
    return when (expression.operator) {
        UnaryOperatorExpression.Operator.NOT -> !evaluate(subject)
    }

}
