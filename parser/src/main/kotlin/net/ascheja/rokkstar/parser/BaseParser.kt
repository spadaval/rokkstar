package net.ascheja.rokkstar.parser

import net.ascheja.rokkstar.ast.Identifier
import net.ascheja.rokkstar.parser.Token.*

open class BaseParser(lastNameDelegate: LastNameDelegate) {

    protected var lastName: String? by lastNameDelegate

    infix fun Token.mustBe(expectation: (Token) -> Unit): Token {
        expectation(this)
        return this
    }

    infix fun Token.mustBe(expectation: Token): Token = mustBe {
        if (type != expectation.type) {
            throw UnexpectedTokenException("expected token to be of type ${expectation.type}, found $type")
        }
        if (text.lowercase() != expectation.text.lowercase()) {
            throw UnexpectedTokenException("expected token text '${expectation.text}', found '$text'${it.getPositionInfo()}")
        }
    }

    private infix fun Token.mustBe(expectation: Type): Token = mustBe {
        if (type != expectation) {
            throw UnexpectedTokenException("expected token to be of type $expectation, got $type${it.getPositionInfo()}")
        }
    }

    protected fun any(vararg tokens: Token): (Token) -> Unit = {
        var found = false
        for (token in tokens) {
            if (token == it) {
                found = true
            }
        }
        if (!found) {
            throw UnexpectedTokenException("expected either of ${tokens.toList()}, got $it${it.getPositionInfo()}")
        }
    }

    protected fun Token.getPositionInfo(): String = when (this) {
        is Word -> position.toString()
        is Garbage -> position.toString()
        is StringLiteral -> position.toString()
        is Comment -> position.toString()
        else -> ""
    }

    private fun Position?.toString(): String {
        if (this != null) {
            return toString()
        }
        return ""
    }

    protected fun parseIdentifier(source: TokenSource): Identifier = Identifier(parseName(source))

    private fun parseName(source: TokenSource): String {
        source.current mustBe Type.WORD
        if (source.current in PRONOUNS) {
            source.next()
            return lastName
                ?: throw UnexpectedTokenException("found pronoun, but no identifier has been mentioned previously")
        }
        return if (source.current in COMMON_VARIABLE_PREFIXES) {
            //common variable
            val prefix = source.current.text
            if (source.lookahead(1) is Space) {
                source.next()
            }
            source.next() mustBe Type.WORD
            (prefix + " " + source.current.text).also { if (source.lookahead(1) is Space) source.next() }
        } else {
            //proper variable
            var temp = source.current.text
            while (true) {
                if (source.lookahead(1) is Space) {
                    source.next()
                }
                if (source.lookahead(1).let { it !is Word || it in PROPER_VARIABLE_TERMINATORS }) {
                    break
                }
                source.next()
                temp += " " + source.current.text
            }
            temp
        }.also {
            source.next()
        }
    }
}