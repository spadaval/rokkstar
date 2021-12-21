package net.ascheja.rokkstar.ast

sealed class Expression : Statement()

data class BinaryOperatorExpression(val operator: Operator, val left: Expression, val right: Expression) : Expression() {
    enum class Operator {
        MULTIPLY,
        DIVIDE,

        ADD,
        SUBTRACT,

        EQUALS,
        NOT_EQUALS,
        LESS,
        LESS_EQUALS,
        GREATER,
        GREATER_EQUALS,

        AND,
        OR,
        NOR
    }
}

data class BooleanConstant(val value: Boolean) : Constant()

data class FunctionCallExpression(val identifier: Identifier, val arguments: List<Expression>) : Expression()

sealed class Constant : Expression()

object NullConstant : Constant()

data class NumberConstant(val value: Double) : Constant()

data class StringConstant(val value: String) : Constant()

data class UnaryOperatorExpression(val operator: Operator, val expression: Expression) : Expression() {


    enum class Operator {
        NOT
    }
}

object UndefinedConstant : Constant()

data class VariableLookup(val identifier: Identifier) : Expression()