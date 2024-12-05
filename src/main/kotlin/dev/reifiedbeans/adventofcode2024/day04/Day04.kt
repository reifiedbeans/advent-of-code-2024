package dev.reifiedbeans.adventofcode2024.day04

import dev.reifiedbeans.adventofcode2024.util.assertEquals
import dev.reifiedbeans.adventofcode2024.util.getInput
import dev.reifiedbeans.adventofcode2024.util.pop

private fun part1(input: List<List<Char>>): Int {
    val indices = input.indices.flatMap { i -> input[i].indices.map { j -> i to j } }
    return indices.sumOf { countMatches(input, it) }
}

private fun part2(input: List<List<Char>>): Int {
    TODO()
}

private fun countMatches(wordSearch: List<List<Char>>, currentIndex: Pair<Int, Int>): Int {
    val directions = listOf(
        -1 to -1, // Left above diagonal
        -1 to 0, // Above
        -1 to 1, // Right above diagonal
        1 to -1, // Left below diagonal
        1 to 0, // Below
        1 to 1, // Right below diagonal
        0 to -1, // Left
        0 to +1, // Right
    )

    return directions.count { direction -> isXmas(wordSearch, currentIndex, direction) }
}

private fun isXmas(
    wordSearch: List<List<Char>>,
    currentIndex: Pair<Int, Int>,
    direction: Pair<Int, Int>,
    letterQueue: List<Char> = listOf('X', 'M', 'A', 'S'),
): Boolean {
    val (currentLetter, newQueue) = letterQueue.pop()
    val nextIndex = currentIndex + direction

    return when {
        wordSearch[currentIndex] == currentLetter && newQueue.isEmpty() -> true

        wordSearch[currentIndex] == currentLetter && isValidIndex(nextIndex, wordSearch) -> {
            isXmas(wordSearch, nextIndex, direction, newQueue)
        }

        else -> false
    }
}

private operator fun List<List<Char>>.get(index: Pair<Int, Int>) = this[index.first][index.second]

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = Pair(this.first + other.first, this.second + other.second)

private fun isValidIndex(index: Pair<Int, Int>, wordSearch: List<List<Char>>): Boolean {
    val (i, j) = index
    return i in wordSearch.indices && j in wordSearch[i].indices
}

private fun List<String>.parse() = this.map(String::toList)

fun main() {
    val testInput = getInput("Day04_test").readLines().parse()
    assertEquals(18, part1(testInput))
    assertEquals(-1, part2(testInput))

    val input = getInput("Day04").readLines().parse()
    println("Part 1: ${part1(input)}")
    println("Part 1: ${part2(input)}")
}
