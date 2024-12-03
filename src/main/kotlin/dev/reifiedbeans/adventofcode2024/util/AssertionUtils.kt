package dev.reifiedbeans.adventofcode2024.util

/**
 * Simple reimplementation of JUnit's assertEquals method.
 */
fun assertEquals(expected: Any, actual: Any) {
    if (expected != actual) throw AssertionError("Expected <$expected> but was <$actual>")
}
