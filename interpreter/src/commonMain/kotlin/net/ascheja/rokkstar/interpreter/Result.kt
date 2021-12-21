package net.ascheja.rokkstar.interpreter

import arrow.core.Either
import net.ascheja.rokkstar.typesystem.Value

typealias ExecutionResult = Either<ExecutionError, Result>

class ExecutionError

sealed class Result {
    data class Return(val value: Value) : Result()

    object Continue : Result()

    object Break : Result()

    object Proceed : Result()
}