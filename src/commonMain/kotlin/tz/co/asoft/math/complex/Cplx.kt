package tz.co.asoft.math.complex

import tz.co.asoft.math.geo.Angle
import tz.co.asoft.math.geo.cos
import tz.co.asoft.math.geo.rad
import tz.co.asoft.math.geo.sin
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.pow
import kotlin.math.sqrt

data class Cplx(var x: Double, var y: Double) {
    companion object {
        val one = Cplx(1, 0)
        val i = Cplx(0, 1)
        val j = Cplx(0, 1)
        val zero = Cplx(0, 0)
    }

    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(mag: Number, angle: Angle) : this(mag.toDouble() * cos(angle), mag.toDouble() * sin(angle))

    val magSq get() = x * x + y * y
    val mag get() = sqrt(magSq)

    val arg: Angle get() = atan2(y, x).rad

    val isReal get() = x != 0.0 && y == 0.0
    val isImaginary get() = x == .0 && y != 0.0

    val conjugate get() = !this

    operator fun plus(c: Cplx) = Cplx(x + c.x, y + c.y)

    operator fun unaryMinus(): Cplx = this * -1.0

    operator fun minus(c: Cplx) = this + (-c)

    operator fun times(n: Number) = Cplx(x * n.toDouble(), y * n.toDouble())

    operator fun times(c: Cplx) = Cplx(x * c.x - y * c.y, x * c.y + y * c.x)

    operator fun div(n: Number) = this * (1 / n.toDouble())

    operator fun div(c: Cplx) = (this * !c) / c.magSq

    fun pow(exp: Double) = Cplx(mag.pow(exp), arg * exp)

    fun pow(n: Int): Cplx = when {
        n > 0 -> {
            var z = one
            repeat(n) { z *= this }
            z
        }
        n < 0 -> one / pow(-n)
        else -> one
    }

    operator fun not() = Cplx(x, -y)

    fun toFullString() = when {
        y < 0.0 -> "$x - ${-y}i"
        else -> "$x + ${y}i"
    }

    override operator fun equals(other: Any?) = when (other) {
        is Cplx -> x == other.x && y == other.y
        else -> false
    }

    override fun toString() = when {
        x == 0.0 && y == 0.0 -> "0"
        isReal -> x.toString()
        isImaginary -> when {
            y < 0.0 -> "-${-y}i"
            else -> "${y}i"
        }
        else -> toFullString()
    }
}