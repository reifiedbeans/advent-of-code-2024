package dev.reifiedbeans.adventofcode2024.util

/**
 * Simple reimplementation of JUnit's assertEquals method.
 */
inline fun <reified T : Any> assertEquals(expected: T, actual: T) {
    if (expected != actual) throw AssertionError("Expected <$expected> but was <$actual>")
}
