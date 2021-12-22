package net.ascheja.rokkstar.interpreter

import net.ascheja.rokkstar.ast.*
import net.ascheja.rokkstar.typesystem.values.NumberValue
import net.ascheja.rokkstar.typesystem.values.StringValue

fun RockstarVM.runStatement(statement: Statement): Result {
    return when (statement) {
        is AssignmentStatement -> {
            this[statement.identifier] = this.evaluate(statement.expression)
            Result.Proceed
        }
        is BlockStatement -> {
            statement.statements.forEach {
                val result = runStatement(it)
                if (result != Result.Proceed)
                    return result
            }
            Result.Proceed
        }
        //control flow
        is IfStatement ->
            if (this.evaluate(statement.condition).toBoolean())
                this.runStatement(statement.thenBlock)
            else if (statement.elseBlock != null)
                this.runStatement(statement.elseBlock!!)
            else Result.Proceed

        is WhileLoopStatement -> runWhileLoop(statement)
        is UntilLoopStatement -> runUntilLoop(statement)

        is BreakStatement -> Result.Break
        is ContinueStatement -> Result.Continue
        is ReturnStatement -> Result.Return(this.evaluate(statement.expression))

        is IncrementStatement -> {
            val oldValue = this.getVariable(statement.identifier).toNumber()
            this[statement.identifier] = NumberValue(oldValue + statement.amount)
            Result.Proceed
        }
        is DecrementStatement -> {
            val oldValue = this.getVariable(statement.identifier).toNumber()
            this[statement.identifier] = NumberValue(oldValue - statement.amount)
            Result.Proceed
        }


        is FunctionDeclaration -> {
            this[statement.identifier] = statement
            Result.Proceed
        }

        is PrintLineStatement -> {
            this.outputFn(this.evaluate(statement.expression))
            Result.Proceed
        }

        is ReadLineStatement -> {
            this[statement.identifier] = StringValue(readln())
            Result.Proceed
        }
        is Expression -> Result.Return(evaluate(statement))
    }
}

private fun RockstarVM.runWhileLoop(statement: WhileLoopStatement): Result {
    loop@ while (evaluateBoolean(statement.condition)) {
        for (it in statement.body) {
            when (val result = this.runStatement(it)) {
                is Result.Proceed -> Unit
                is Result.Continue -> continue@loop
                is Result.Break -> return Result.Proceed
                is Result.Return -> return result
            }
        }
    }
    return Result.Proceed
}

private fun RockstarVM.runUntilLoop(statement: UntilLoopStatement): Result {
    loop@ while (true) {
        if (evaluateBoolean(statement.condition)) {
            return Result.Proceed
        }
        when (val result = runStatement(statement.body)) {
            is Result.Break -> return Result.Proceed
            is Result.Continue -> continue@loop
            is Result.Return -> return result
            is Result.Proceed -> Unit
        }
    }
}