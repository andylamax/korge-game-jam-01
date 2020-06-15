package tz.co.asoft.lab1

import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import tz.co.asoft.tools.Loc

data class Snake(
    var segments: MutableList<Loc>,
    var delta: Delta,
    val headColor: RGBA
) {
    companion object {
        const val SNAKE_RATE = 20
    }

    constructor(x: Int, y: Int, delta: Delta, headColor: RGBA = Colors.YELLOW) : this(mutableListOf(Loc(x, y)), delta, headColor)

    val head get() = segments.last()
    val body get() = segments - head
    val headInBody get() = body.any { it == head }
    var snakeRate = 0
    var isDead = false
    var isAlive
        set(value) {
            isDead = !value
        }
        get() = !isDead

    fun move() {
        for (i in 0 until segments.size - 1) {
            segments[i] = segments[i + 1]
        }
        segments[segments.size - 1] = head + delta.loc
    }

    fun grow() {
        segments.add(0, Loc(-1, -1))
    }
}