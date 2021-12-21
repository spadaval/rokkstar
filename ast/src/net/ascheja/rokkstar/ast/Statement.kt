package net.ascheja.rokkstar.ast

sealed class Statement

data class BlockStatement(val statements: List<Statement>) : Statement(), Iterable<Statement> by statements {
    constructor(statement: Statement) : this(listOf(statement))
}

object ContinueStatement : Statement()
object BreakStatement : Statement()
data class ReturnStatement(val expression: Expression) : Statement()
data class UntilLoopStatement(val condition: Expression, val body: BlockStatement) : Statement()
data class WhileLoopStatement(val condition: Expression, val body: BlockStatement) : Statement()

data class FunctionDeclaration(
    val identifier: Identifier,
    val parameters: List<Identifier>,
    val body: BlockStatement
) : Statement()

data class IfStatement(
    val condition: Expression,
    val thenBlock: BlockStatement,
    val elseBlock: BlockStatement? = null
) : Statement()

data class IncrementStatement(val identifier: Identifier, val amount: Int) : Statement()
data class DecrementStatement(val identifier: Identifier, val amount: Int) : Statement()
data class AssignmentStatement(val identifier: Identifier, val expression: Expression) : Statement()


data class PrintLineStatement(val expression: Expression) : Statement()
data class ReadLineStatement(val identifier: Identifier) : Statement()
