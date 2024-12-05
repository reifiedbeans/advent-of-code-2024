package dev.reifiedbeans.adventofcode2024.util

/**
 * Removes and returns the first element in a non-mutable [List] along with a copy of the list with the element removed.
 */
fun <T> List<T>.pop() = this.firstOrNull() to this.drop(1)

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
