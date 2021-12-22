package net.ascheja.rokkstar.interpreter

import net.ascheja.rokkstar.typesystem.Value

sealed class Result {
    data class Return(val value: Value) : Result()

    object Continue : Result()

    object Break : Result()

    object Proceed : Result()
}