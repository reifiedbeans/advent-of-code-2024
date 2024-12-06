package dev.reifiedbeans.adventofcode2024.util

data class Vector(val y: Int, val x: Int)

enum class Direction(val vector: Vector) {
    NORTH(Vector(-1, 0)),
    EAST(Vector(0, 1)),
    SOUTH(Vector(1, 0)),
    WEST(Vector(0, -1)),
}

data class Position(val y: Int, val x: Int) {
    operator fun plus(v: Vector) = Position(this.y + v.y, this.x + v.x)
    operator fun plus(d: Direction) = this + d.vector
}

data class Bounds(val height: Int, val length: Int) {
    operator fun contains(p: Position) = p.y in 0..<height && p.x in 0..<length
}
