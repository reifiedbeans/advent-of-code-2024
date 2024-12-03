package dev.reifiedbeans.adventofcode2024.day02

import dev.reifiedbeans.adventofcode2024.util.asSequence
import dev.reifiedbeans.adventofcode2024.util.assertEquals
import dev.reifiedbeans.adventofcode2024.util.getInput
import dev.reifiedbeans.adventofcode2024.util.withIndexRemoved

private typealias Report = List<Int>

private fun part1(reports: List<Report>) = reports.count { it.isSafe() }

private fun part2(reports: List<Report>) = reports.count { it.isSafe() || dampenedReportsFor(it).any(Report::isSafe) }

private fun dampenedReportsFor(report: Report) = report.indices.asSequence { report.withIndexRemoved(it) }

private fun Report.isSafe(): Boolean {
    val deltas = this.windowed(2) { it[1] - it[0] }
    return deltas.all { it in -3..-1 } || deltas.all { it in 1..3 }
}

private fun List<String>.parse() = this.map { it.split(" ").map(String::toInt) }

fun main() {
    val testInput = getInput("Day02_test").readLines().parse()
    assertEquals(2, part1(testInput))
    assertEquals(4, part2(testInput))

    val input = getInput("Day02").readLines().parse()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
