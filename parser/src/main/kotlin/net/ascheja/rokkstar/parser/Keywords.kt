package net.ascheja.rokkstar.parser

val PRONOUNS = setOf(
        "it", "he", "she", "him", "her", "they", "them", "ze", "hir", "zie", "zir", "xe", "xem", "ve", "ver"
    ).map { Token.Word(it) }

    val COMMON_VARIABLE_PREFIXES = setOf(
        "a", "an", "the", "my", "your"
    ).map { Token.Word(it) }

    val KW_WHILE = Token.Word("While")
    val KW_UNTIL = Token.Word("Until")
    val KW_IF = Token.Word("If")
    val KW_ELSE = Token.Word("Else")
    val KW_GIVE = Token.Word("Give")
    val KW_BACK = Token.Word("back")
    val KW_LISTEN = Token.Word("Listen")
    val KW_TO = Token.Word("to")
    val KW_SAY = Token.Word("Say")
    val KW_SCREAM = Token.Word("Scream")
    val KW_SHOUT = Token.Word("Shout")
    val KW_WHISPER = Token.Word("Whisper")
    val KW_SAYS = Token.Word("says")
    val KW_PUT = Token.Word("Put")
    val KW_INTO = Token.Word("into")

    val KW_TAKING = Token.Word("taking")
    val KW_TAKES = Token.Word("takes")

    val KW_APO_N_APO = Token.Word("'n'")
    val KW_AND = Token.Word("and")
    val KW_OR = Token.Word("or")
    val KW_NOR = Token.Word("nor")
    val KW_NOT = Token.Word("not")

    val KW_IS = Token.Word("is")
    val KW_WAS = Token.Word("was")
    val KW_WERE = Token.Word("were")
    val KW_ISNT = Token.Word("isn't")
    val KW_AINT = Token.Word("ain't")

    val KW_AS = Token.Word("as")
    val KW_THAN = Token.Word("than")

    val KW_PLUS = Token.Word("plus")
    val KW_WITH = Token.Word("with")
    val KW_MINUS = Token.Word("minus")
    val KW_WITHOUT = Token.Word("without")
    val KW_TIMES = Token.Word("times")
    val KW_OF = Token.Word("of")
    val KW_OVER = Token.Word("over")

    val KW_CONTINUE = Token.Word("Continue")
    val KW_TAKE = Token.Word("Take")
    val KW_IT = Token.Word("it")
    val KW_THE = Token.Word("the")
    val KW_TOP = Token.Word("top")

    val KW_BREAK = Token.Word("Break")
    val KW_BUILD = Token.Word("Build")
    val KW_UP = Token.Word("up")

    val KW_KNOCK = Token.Word("Knock")
    val KW_DOWN = Token.Word("down")

    val KW_MYSTERIOUS = Token.Word("mysterious")

    val NULL_ALIASES = setOf("null", "nothing", "nowhere", "nobody", "empty", "gone").map { Token.Word(it) }
    val TRUE_ALIASES = setOf("true", "right", "yes", "ok").map { Token.Word(it) }
    val FALSE_ALIASES = setOf("false", "wrong", "no", "lies").map { Token.Word(it) }

    val GREATER_ALIASES = setOf("higher", "greater", "bigger", "stronger").map { Token.Word(it) }
    val LESS_ALIASES = setOf("lower", "less", "smaller", "weaker").map { Token.Word(it) }
    val GREATER_EQUAL_ALIASES = setOf("high", "great", "big", "strong").map { Token.Word(it) }
    val LESS_EQUAL_ALIASES = setOf("low", "little", "small", "weak").map { Token.Word(it) }

    val AMPERSAND = Token.Garbage('&')
    val COMMA = Token.Garbage(',')

    val PROPER_VARIABLE_TERMINATORS = setOf(
        Token.Eol, Token.Eof, KW_SAYS, KW_AND, KW_OR, KW_NOR, KW_IS, KW_ISNT, KW_AINT, KW_TAKES, KW_TAKING,
        COMMA, AMPERSAND, KW_APO_N_APO, KW_UP, KW_DOWN, KW_INTO, KW_WAS, KW_WERE,
        KW_PLUS, KW_WITH, KW_MINUS, KW_WITHOUT, KW_TIMES, KW_OF, KW_OVER
    )
    val NUMERIC_CHECK = Regex("[0-9]+")
