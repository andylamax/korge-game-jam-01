package tz.co.asoft.math.vector.internal

import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.math.vector.Vec3

internal class Vec2Impl(override var x: Double, override var y: Double) : Vec2 {
    override operator fun plus(v: Vec2) = Vec2Impl(x + v.x, y + v.y)
    override operator fun times(n: Number) = Vec2Impl(x * n.toDouble(), y * n.toDouble())

    override fun equals(other: Any?) = when (other) {
        is Vec3 -> other.x == x && other.y == y && other.z == 0.0
        is Vec2 -> other.x == x && other.y == y
        else -> false
    }

    override fun toString() = "($x,$y)"
}