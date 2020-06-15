package tz.co.asoft.tools

data class Loc(val x: Int, val y: Int) {
    constructor(x: Number, y: Number) : this(x.toInt(), y.toInt())

    operator fun times(n: Number) = Loc(x * n.toDouble(), y * n.toDouble())

    operator fun plus(l: Loc) = copy(x = x + l.x, y = y + l.y)
}