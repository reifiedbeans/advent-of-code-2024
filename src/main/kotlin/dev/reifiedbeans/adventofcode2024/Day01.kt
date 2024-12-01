package dev.reifiedbeans.adventofcode2024

import kotlin.math.abs

private fun part1(list1: List<Int>, list2: List<Int>): Int {
    val pairs = list1.sorted() zip list2.sorted()
    return pairs.sumOf { (first, second) -> abs(first - second) }
}

private fun part2(list1: List<Int>, list2: List<Int>): Int {
    val frequencies = list2.groupingBy { it }.eachCount()
    return list1.sumOf { it * frequencies.getOrDefault(it, 0) }
}

private fun List<String>.parse() = this.map { it.split("\\s+".toRegex()).map(String::toInt).toPair() }.unzip()

fun main() {
    val (testList1, testList2) = getInput("Day01_test").readLines().parse()
    assertEquals(11, part1(testList1, testList2))
    assertEquals(31, part2(testList1, testList2))

    val (list1, list2) = getInput("Day01").readLines().parse()
    println("Part 1: ${part1(list1, list2)}")
    println("Part 2: ${part2(list1, list2)}")
}
