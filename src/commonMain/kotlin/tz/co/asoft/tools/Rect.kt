package tz.co.asoft.tools

import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.math.vector.internal.Vec2Impl

data class Rect(var left: Double, var right: Double, var top: Double, var bottom: Double) {
    constructor(left: Number, right: Number, top: Number, bottom: Number) : this(left.toDouble(), right.toDouble(), top.toDouble(), bottom.toDouble())
    constructor(topLeft: Vec2, bottomRight: Vec2) : this(topLeft.x, bottomRight.x, topLeft.y, bottomRight.y)
    constructor(topLeft: Vec2, width: Number, height: Number) : this(topLeft, topLeft + Vec2(width, height))

    companion object {
        operator fun invoke(center: Vec2, halfWidth: Number, halfHeight: Number): Rect {
            val ds = Vec2(halfWidth, halfHeight)
            return Rect(center - ds, center + ds)
        }
    }

    fun overlapsWith(other: Rect) = right > other.right && left < other.left && bottom > other.top && top < other.bottom

    fun offseted(v: Vec2) = Rect(left - v.x, right + v.x, top - v.y, bottom + v.y)
    fun offseted(n: Number) = offseted(Vec2(n, n))

    val center: Vec2 get() = Vec2Impl((left + right) / 2, (top + bottom) / 2)

    val width get() = right - left
    val height get() = bottom - top

    val topLeft get() = Vec2(left, top)
    val topRight get() = Vec2(right, top)
    val bottomLeft get() = Vec2(left, bottom)
    val bottomRight get() = Vec2(right, bottom)
}