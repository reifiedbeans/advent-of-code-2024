package dev.reifiedbeans.adventofcode2024.day05

import dev.reifiedbeans.adventofcode2024.util.assertEquals
import dev.reifiedbeans.adventofcode2024.util.getInput
import dev.reifiedbeans.adventofcode2024.util.toPair

private typealias Page = Int

private data class Input(val pageOrderingRules: Map<Page, Set<Page>>, val updates: List<List<Page>>)

private fun part1(input: Input): Int {
    val (pageOrderingRules, updates) = input
    return updates.filter { it.orderIsCorrect(pageOrderingRules) }
        .sumOf(List<Page>::middlePage)
}

private fun part2(input: Input): Int {
    val (pageOrderingRules, updates) = input
    return updates.filter { !it.orderIsCorrect(pageOrderingRules) }
        .map { it.correctOrder(pageOrderingRules) }
        .sumOf(List<Page>::middlePage)
}

private fun List<Page>.orderIsCorrect(pageOrderingRules: Map<Page, Set<Page>>): Boolean {
    val pagesAfter = mutableSetOf<Page>()

    for (page in this.reversed()) {
        val pagesRequiredBefore = pageOrderingRules[page] ?: emptySet()
        if (pagesAfter.any { it in pagesRequiredBefore }) return false
        pagesAfter += page
    }

    return true
}

private fun List<Page>.correctOrder(pageOrderingRules: Map<Page, Set<Page>>): List<Page> {
    val pageSet = this.toSet()
    return this.sortedBy { page ->
        val pagesRequiredBefore = pageOrderingRules[page] ?: emptySet()
        pagesRequiredBefore.intersect(pageSet).size
    }
}

private val List<Page>.middlePage
    get() = this[this.size / 2]

private fun String.parse(): Input {
    val (rawRules, rawUpdates) = this.trim('\n').split("\n\n")

    val rules = rawRules.lines()
        .map { it.split('|').map(String::toInt).toPair() }
        .groupBy(keySelector = { it.second }, valueTransform = { it.first })
        .mapValues { it.value.toSet() }

    val updates = rawUpdates.lines()
        .map { it.split(',').map(String::toInt) }

    return Input(rules, updates)
}

fun main() {
    val testInput = getInput("Day05_test").readText().parse()
    assertEquals(143, part1(testInput))
    assertEquals(123, part2(testInput))

    val input = getInput("Day05").readText().parse()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
