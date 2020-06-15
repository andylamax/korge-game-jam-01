package tz.co.asoft.math.vector.internal

import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.math.vector.Vec3
import tz.co.asoft.math.vector.toVec3

internal class Vec3Impl(override var x: Double, override var y: Double, override var z: Double) : Vec3 {
    override operator fun plus(v: Vec2) = Vec3Impl(x + v.x, y + v.y, z)

    override operator fun plus(v: Vec3) = Vec3Impl(x + v.x, y + v.y, z + v.z)

    override fun minus(v: Vec2): Vec3 = Vec3Impl(x - v.x, y - v.y, z)

    override fun times(v: Vec3) = Vec3Impl(y * v.z - v.y * z, v.x * z - x * v.z, x * v.y - v.x * y)

    override fun times(v: Vec2) = this * v.toVec3()

    override operator fun times(n: Number) = Vec3Impl(x * n.toDouble(), y * n.toDouble(), z * n.toDouble())

    override fun equals(other: Any?) = when (other) {
        is Vec3 -> other.x == x && other.y == y && other.z == z
        is Vec2 -> other.x == x && other.y == y && z == 0.0
        else -> false
    }

    override fun toString() = "($x,$y,$z)"
}