package tz.co.asoft.math.vector

import kotlin.math.sqrt

interface Vec2 {
    var x: Double
    var y: Double

    operator fun component1() = x
    operator fun component2() = y

    val lengthSq get() = this dot this
    val length get() = sqrt(lengthSq)

    operator fun plus(v: Vec2): Vec2

    operator fun plus(v: Vec3): Vec3 = v + this

    operator fun minus(v: Vec2) = this + (v * -1)

    operator fun unaryMinus() = this * -1

    operator fun times(n: Number): Vec2

    operator fun div(n: Number) = this * (1 / n.toDouble())

    fun normalized(): Vec2 = if (lengthSq != 0.0) this / length else this

    infix fun x(n: Number) = this * n

    infix fun dot(v: Vec2) = x * v.x + y * v.y

    infix fun o(v: Vec2) = this dot v
}