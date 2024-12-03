package dev.reifiedbeans.adventofcode2024.day03

import dev.reifiedbeans.adventofcode2024.day03.Operation.Companion.parseOperations
import dev.reifiedbeans.adventofcode2024.day03.Operation.Disable
import dev.reifiedbeans.adventofcode2024.day03.Operation.Enable
import dev.reifiedbeans.adventofcode2024.day03.Operation.Multiply
import dev.reifiedbeans.adventofcode2024.util.assertEquals
import dev.reifiedbeans.adventofcode2024.util.getInput

private fun part1(input: String): Int {
    val operations = parseOperations(input, validOperations = setOf(Multiply::class))
    return operations.sumOf {
        when (it) {
            is Multiply -> it.result
            else -> 0
        }
    }
}

private fun part2(input: String): Int {
    val operations = parseOperations(input)
    var result = 0
    var enabled = true

    for (op in operations) {
        when {
            op is Enable -> enabled = true
            op is Disable -> enabled = false
            op is Multiply && enabled -> result += op.result
        }
    }

    return result
}

fun main() {
    val testInput = getInput("Day03_test").readText()
    assertEquals(161, part1(testInput))
    assertEquals(48, part2(testInput))

    val input = getInput("Day03").readText()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
