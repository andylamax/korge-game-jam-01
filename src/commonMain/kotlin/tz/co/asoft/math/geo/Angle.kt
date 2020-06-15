package tz.co.asoft.math.geo

import kotlin.math.*

sealed class Angle(val degrees: Double) {
    val radians get() = degrees * PI / 180

    class Degree(value: Number) : Angle(value.toDouble())
    class Radians(value: Number) : Angle(value.toDouble() * 180 / PI)

    operator fun unaryMinus() = this * -1
    operator fun times(n: Number): Angle = Degree(degrees * n.toDouble())
    operator fun div(n: Number) = this * (1 / n.toDouble())
    operator fun plus(a: Angle): Angle = Degree(degrees + a.degrees)
    override fun equals(other: Any?) = other is Angle && other.degrees == degrees
    override fun toString() = "$degrees${Typography.degree}"
}

val Number.deg get() = Angle.Degree(this)
val Number.rad get() = Angle.Radians(this)

fun sin(a: Angle) = sin(a.radians)
fun cos(a: Angle) = cos(a.radians)
fun tan(a: Angle) = tan(a.radians)