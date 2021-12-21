package values

import net.ascheja.rokkstar.typesystem.Value
import net.ascheja.rokkstar.typesystem.ValueConversionException
import net.ascheja.rokkstar.typesystem.values.*
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

typealias Expression = () -> Value

abstract class ValueTest {

    abstract val conversionExamples: Set<ConversionExample>

    abstract val expressionExamples: Set<ExpressionExample>

    data class ConversionExample(val value: Value, val asString: String, val asBoolean: Boolean, val asNumber: Double?)

    data class ExpressionExample(val action: Expression, val expectedResult: Any)

    protected fun Value.withConversions(
        asString: String,
        asBoolean: Boolean,
        asNumber: Double? = null
    ): ConversionExample {
        return ConversionExample(this, asString, asBoolean, asNumber)
    }

    protected infix fun Expression.shouldFailWith(e: Exception): ExpressionExample {
        return ExpressionExample(this, e)
    }

    protected infix fun Expression.shouldSucceedWith(value: Value): ExpressionExample {
        return ExpressionExample(this, value)
    }

    @Test
    fun `string representation`() {
        for (example in conversionExamples) {
            assertEquals(example.asString, example.value.toString())
        }
    }

    @Test
    fun truthiness() {
        for (example in conversionExamples) {
            assertEquals(example.asBoolean, example.value.toBoolean())
        }
    }

    @Test
    fun `numeric representation`() {
        for (example in conversionExamples) {
            if (example.asNumber == null) {
                assertTrue(
                    try {
                        example.value.toNumber(); false
                    } catch (e: ValueConversionException) {
                        true
                    }
                )
            } else {
                assertEquals(example.asNumber, example.value.toNumber(), 0.0)
            }
        }
    }

    @Test
    fun expressions__shouldFail() {
        expressionExamples.filter { it.expectedResult is Exception }.forEachIndexed { idx, example ->
            try {
                example.action.invoke()
                fail("#$idx: result should be an exception")
            } catch (e: Exception) {
                assertEquals((example.expectedResult as Throwable).message, e.message)
            }
        }
    }


    @Test
    fun expressions__shouldSucceed() {
        expressionExamples.filter { it.expectedResult !is Exception }.forEachIndexed { idx, example ->
            val result = example.action.invoke()
            when (example.expectedResult) {
                is UndefinedValue -> {
                    assertTrue(result is UndefinedValue, "#$idx: result should be undefined")
                }
                is NullValue -> {
                    assertTrue(result is NullValue, "#$idx: result should be null")
                }
                is BooleanValue -> {
                    assertTrue(result is BooleanValue, "#$idx: result should be boolean")
                    assertEquals(
                        example.expectedResult.value,
                        result.value,
                        "#$idx: not matching expected value"
                    )
                }
                is NumberValue -> {
                    assertTrue(result is NumberValue, "#$idx: result should be a number")
                    assertEquals(
                        example.expectedResult.value,
                        result.value,
                        0.01,
                        "#$idx: not matching expected value",
                    )
                }
                is StringValue -> {
                    assertTrue(result is StringValue, "#$idx: result should be a string")
                    assertEquals(
                        example.expectedResult.value,
                        result.value,
                        "#$idx: not matching expected value",
                    )
                }
            }
        }
    }
}