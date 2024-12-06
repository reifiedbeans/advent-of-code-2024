package dev.reifiedbeans.adventofcode2024.day06

import dev.reifiedbeans.adventofcode2024.day06.ProcessedInput.Companion.parse
import dev.reifiedbeans.adventofcode2024.util.Bounds
import dev.reifiedbeans.adventofcode2024.util.Direction
import dev.reifiedbeans.adventofcode2024.util.Position
import dev.reifiedbeans.adventofcode2024.util.assertEquals
import dev.reifiedbeans.adventofcode2024.util.getInput

private operator fun Direction.next() = when (this) {
    Direction.NORTH -> Direction.EAST
    Direction.EAST -> Direction.SOUTH
    Direction.SOUTH -> Direction.WEST
    Direction.WEST -> Direction.NORTH
}

private operator fun Direction.inc() = this.next()

private data class ProcessedInput(
    val mapBounds: Bounds,
    val obstructionPositions: Set<Position>,
    val guardStartingPosition: Position,
) {
    companion object {
        fun String.parse(): ProcessedInput {
            val lines = this.lines()
            val obstructionPositions = mutableSetOf<Position>()
            var guardStartingPosition: Position? = null

            for ((i, line) in lines.withIndex()) {
                for ((j, char) in line.withIndex()) {
                    val position = Position(i, j)
                    when (char) {
                        '#' -> obstructionPositions += position
                        '^' -> guardStartingPosition = position
                    }
                }
            }

            require(guardStartingPosition != null) {
                "Could not find guard starting position in input"
            }

            val mapBounds = Bounds(height = lines.size, length = lines[0].length)
            return ProcessedInput(mapBounds, obstructionPositions, guardStartingPosition)
        }
    }

    fun getObstructions(startingPosition: Position, direction: Direction) = sequence {
        var currentPosition = startingPosition
        while (currentPosition in mapBounds) {
            val nextPosition = currentPosition + direction
            if (nextPosition in obstructionPositions) {
                yield(nextPosition)
            }

            currentPosition = nextPosition
        }
    }
}

private fun part1(input: ProcessedInput): Int {
    var currentDirection = Direction.NORTH
    var currentPosition = input.guardStartingPosition

    val visitedPositions = mutableSetOf<Position>()
    while (currentPosition in input.mapBounds) {
        visitedPositions += currentPosition

        var nextPosition = currentPosition + currentDirection
        if (nextPosition in input.obstructionPositions) {
            currentDirection++
            nextPosition = currentPosition + currentDirection
        }

        currentPosition = nextPosition
    }

    return visitedPositions.size
}

private fun part2(input: ProcessedInput): Int {
    var currentDirection = Direction.NORTH
    var currentPosition = input.guardStartingPosition

    val visitedPositions = mutableSetOf<Position>()
    val obstructionsHit = mutableSetOf<Position>()
    val potentialObstructionLocations = mutableListOf<Position>()
    while (currentPosition in input.mapBounds) {
        val alreadyVisited = currentPosition in visitedPositions
        if (!alreadyVisited) visitedPositions += currentPosition

        var nextPosition = currentPosition + currentDirection
        if (nextPosition in input.obstructionPositions) {
            obstructionsHit += nextPosition
            currentDirection++
            nextPosition = currentPosition + currentDirection
        }

        // FIXME: works on test input, but not on puzzle input
        if (nextPosition in input.mapBounds) {
            val potentialNextDirection = currentDirection.next()
            if (input.getObstructions(currentPosition, potentialNextDirection).any { it in obstructionsHit }) {
                potentialObstructionLocations += nextPosition
            }
        }

        currentPosition = nextPosition
    }

    return potentialObstructionLocations.size
}

fun main() {
    val testInput = getInput("Day06_test").readText().parse()
    assertEquals(41, part1(testInput))
    assertEquals(6, part2(testInput))

    val input = getInput("Day06").readText().parse()
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
