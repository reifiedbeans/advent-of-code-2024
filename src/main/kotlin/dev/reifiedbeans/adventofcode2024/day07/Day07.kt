package dev.reifiedbeans.adventofcode2024.day07

import dev.reifiedbeans.adventofcode2024.util.assertEquals
import dev.reifiedbeans.adventofcode2024.util.generatePermutations
import dev.reifiedbeans.adventofcode2024.util.getInput

private data class Equation(val testValue: Long, val nums: List<Long>) {
    companion object {
        private val equationRegex = Regex("(\\d+): ([\\d ]+)")

        fun fromString(s: String): Equation {
            val match = equationRegex.matchEntire(s)
            require(match != null && match.groupValues.size == 3) { "Equation is malformed" }
            val testValue = match.groupValues[1].toLong()
            val nums = match.groupValues[2].split(' ').map(String::toLong)
            return Equation(testValue, nums)
        }
    }

    fun compute(operations: List<Operation>): Boolean {
        require(operations.size == nums.size - 1) { "Wrong number of operations" }

        val result = nums.drop(1).zip(operations).fold(nums.first()) { result, (num, op) ->
            when (op) {
                Operation.ADD -> result + num
                Operation.MULTIPLY -> result * num
                Operation.CONCATENATE -> "$result$num".toLong()
            }
        }

        return result == testValue
    }
}

private enum class Operation {
    ADD,
    MULTIPLY,
    CONCATENATE,
}

private fun part1(equations: List<Equation>) = computeResult(equations, operations = setOf(Operation.ADD, Operation.MULTIPLY))

private fun part2(equations: List<Equation>) = computeResult(equations, operations = Operation.entries.toSet())

private fun computeResult(equations: List<Equation>, operations: Set<Operation>) = equations.sumOf { equation ->
    val operationsToCheck = generatePermutations(operations, equation.nums.size - 1)
    when {
        operationsToCheck.any { equation.compute(it) } -> equation.testValue
        else -> 0
    }
}

private fun List<String>.parse() = this.map { Equation.fromString(it) }

fun main() {
    val testInput = getInput("Day07_test").readLines().parse()
    assertEquals(3749L, part1(testInput))
    assertEquals(11387L, part2(testInput))

    val input = getInput("Day07").readLines().parse()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
