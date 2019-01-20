package net.ascheja.rokkstar.ast.statements

import net.ascheja.rokkstar.ast.Expression
import net.ascheja.rokkstar.ast.Statement

data class WhileLoopStatement(val condition: Expression, val body: BlockStatement): Statement