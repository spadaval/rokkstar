package net.ascheja.rokkstar.typesystem.values

import net.ascheja.rokkstar.typesystem.UndefinedBehaviorException
import net.ascheja.rokkstar.typesystem.Value
import kotlin.math.absoluteValue

class NumberValue(val value: Double) : Value {
    override fun toBoolean(): Boolean = value != 0.0

    override fun toNumber(): Double = value

    override fun toString(): String = if ((value.toInt()-value).absoluteValue>0) value.toString() else value.toInt().toString()

    override fun hashCode(): Int = value.hashCode()

    override fun equals(other: Any?): Boolean = when (other) {
        is NumberValue -> value == other.value
        is NullValue -> value == 0.0
        is StringValue -> value == other.toNumber()
        else -> false
    }

    override fun plus(other: Value): Value = when (other) {
        is NumberValue -> NumberValue(value + other.value)
        is StringValue -> StringValue(toString() + other)
        is NullValue -> this
        else -> throw UndefinedBehaviorException("+ not defined for number")
    }

    override fun minus(other: Value): Value = when (other) {
        is NumberValue -> NumberValue(value - other.value)
        is StringValue -> NumberValue(value - other.toNumber())
        is NullValue -> this
        else -> throw UndefinedBehaviorException("- not defined for number")
    }

    override fun times(other: Value): Value = when (other) {
        is NumberValue -> NumberValue(value * other.value)
        is StringValue -> StringValue(other.value.repeat(value.toInt()))
        is NullValue -> NumberValue(0.0)
        else -> throw UndefinedBehaviorException("* not defined for number")
    }

    override fun div(other: Value): Value = when (other) {
        is NumberValue -> if (other.value != 0.0) NumberValue(value / other.value) else UndefinedValue
        is StringValue -> NumberValue(value / other.toNumber())
        is NullValue -> UndefinedValue
        else -> throw UndefinedBehaviorException("/ not defined for number")
    }

    override fun inc(): Value = NumberValue(value + 1)

    override fun dec(): Value = NumberValue(value - 1)

    override fun compareTo(other: Value): Int = when (other) {
        is NumberValue -> value.compareTo(other.value)
        is StringValue -> value.compareTo(other.toNumber())
        else -> -1
    }
}