package dev.reifiedbeans.adventofcode2024.util

import java.io.File

fun getInput(name: String) = File("input", "$name.txt")

fun File.readLinesChunked(delimiter: String = "\n\n") = this.readText().trim('\n').split(delimiter).map(String::lines)
