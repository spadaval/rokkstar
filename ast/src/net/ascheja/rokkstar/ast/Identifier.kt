package net.ascheja.rokkstar.ast

class Identifier(val value: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Identifier) return false

        return value.lowercase() == other.value.lowercase()
    }

    override fun hashCode(): Int {
        return value.lowercase().hashCode()
    }

    override fun toString(): String = value
}