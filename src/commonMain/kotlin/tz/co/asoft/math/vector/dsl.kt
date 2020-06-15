package tz.co.asoft.math.vector

import tz.co.asoft.math.vector.internal.Vec2Impl
import tz.co.asoft.math.vector.internal.Vec3Impl

fun Vec2.toVec3(x: Number = component1(), y: Number = component2(), z: Number = 0): Vec3 = Vec3Impl(x.toDouble(), y.toDouble(), z.toDouble())
fun Vec3.toVec2(x: Number = component1(), y: Number = component2()) = Vec2(x, y)

operator fun Vec2.times(v: Vec3): Vec3 = toVec3() * v

infix fun Vec2.x(v: Vec3): Vec3 = this * v

operator fun Number.times(v: Vec2): Vec2 = v * this

operator fun Number.times(v: Vec3): Vec3 = v * this

fun Vec2.copy(x: Number = component1(), y: Number = component2()) = Vec2(x, y)

fun Vec3.copy(x: Number = component1(), y: Number = component2(), z: Number = component3()) = Vec3(x, y, z)

fun Vec(x: Number = 0, y: Number = 0, z: Number = 0) = if (z == 0) {
    Vec2(x, y)
} else {
    Vec3(x, y, z)
}

fun Vec2(x: Number = 0, y: Number = 0): Vec2 = Vec2Impl(x.toDouble(), y.toDouble())

fun Vec3(x: Number = 0, y: Number = 0, z: Number = 0): Vec3 = Vec3Impl(x.toDouble(), y.toDouble(), z.toDouble())

val i = Vec2(1, 0)
val j = Vec2(0, 1)
val k = Vec3(0, 0, 1)