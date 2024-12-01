package dev.reifiedbeans.adventofcode2024

import java.io.File

fun getInput(name: String) = File("input", "$name.txt")

fun File.readLinesChunked(delimiter: String = "\n\n") = this.readText().trim('\n').split(delimiter).map(String::lines)

fun <T : Any> List<T>.toPair(): Pair<T, T> {
    require(this.size == 2) { "List must have only two elements" }
    return this.first() to this.last()
}
