package dev.reifiedbeans.adventofcode2024

import java.io.File

fun getInput(name: String) = File("input", "$name.txt")

fun File.readLinesChunked(delimiter: String = "\n\n") = this.readText().trim('\n').split(delimiter).map(String::lines)

/**
 * Simple reimplementation of JUnit's assertEquals method.
 */
inline fun <reified T : Any> assertEquals(expected: T, actual: T) {
    if (expected != actual) throw AssertionError("Expected <$expected> but was <$actual>")
}

/**
 * Converts a [List] of two elements to a [Pair]. Written for convenience, but I don't condone it. Use caution.
 * @throws IllegalArgumentException if the [List] does not have exactly two elements
 */
fun <T> List<T>.toPair(): Pair<T, T> {
    require(this.size == 2) { "List must have only two elements" }
    return this.first() to this.last()
}

fun <T> List<T>.withIndexRemoved(index: Int) = this.slice(this.indices - index)

/**
 * Convenience function to do a lazy transform on an [Iterable].
 */
fun <T, R> Iterable<T>.asSequence(transform: (T) -> R) = this.asSequence().map(transform)
