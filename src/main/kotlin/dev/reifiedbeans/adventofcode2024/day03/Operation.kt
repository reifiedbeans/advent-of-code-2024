package dev.reifiedbeans.adventofcode2024.day03

import dev.reifiedbeans.adventofcode2024.util.toPair
import kotlin.reflect.KClass

internal typealias OperationSet = Set<KClass<out Operation>>

internal sealed interface Operation {
    companion object {
        private val allOperations = setOf(Enable::class, Disable::class, Multiply::class)

        private val operationCodes = mapOf(
            Enable::class to "do",
            Disable::class to "don't",
            Multiply::class to "mul",
        )

        private fun fromMatchGroups(matchGroups: MatchGroupCollection) = when (val operation = matchGroups[1]!!.value) {
            operationCodes[Enable::class] -> Enable
            operationCodes[Disable::class] -> Disable
            operationCodes[Multiply::class] -> {
                val (a, b) = matchGroups[2]!!.value.split(",").map(String::toInt).toPair()
                Multiply(a, b)
            }

            else -> error("Unknown operation: $operation")
        }

        private fun getRegex(operations: OperationSet): Regex {
            val operationsGroup = operations.joinToString(prefix = "(", separator = "|", postfix = ")") { operationCodes[it]!! }
            return Regex("$operationsGroup\\(([\\d,]*)\\)")
        }

        fun parseOperations(input: String, validOperations: OperationSet = allOperations): Sequence<Operation> {
            val operationRegex = getRegex(validOperations)
            return operationRegex.findAll(input).map { fromMatchGroups(it.groups) }
        }
    }

    data object Enable : Operation

    data object Disable : Operation

    data class Multiply(val a: Int, val b: Int) : Operation {
        val result by lazy { a * b }
    }
}
