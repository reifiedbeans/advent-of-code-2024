package dev.reifiedbeans.adventofcode2024.util

/**
 * Converts a [List] of two elements to a [Pair]. Written for convenience, but I don't condone it. Use caution.
 * @throws IllegalArgumentException if the [List] does not have exactly two elements
 */
fun <T> List<T>.toPair(): Pair<T, T> {
    require(this.size == 2) { "List must have only two elements" }
    return this.first() to this.last()
}

/**
 * Convenience function to do a lazy transform on an [Iterable].
 */
fun <T, R> Iterable<T>.asSequence(transform: (T) -> R) = this.asSequence().map(transform)

fun <T> List<T>.withIndexRemoved(index: Int) = this.slice(this.indices - index)
