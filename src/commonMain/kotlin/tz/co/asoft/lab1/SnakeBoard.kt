package tz.co.asoft.lab1

import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import tz.co.asoft.tools.IGrid
import tz.co.asoft.tools.Loc

class SnakeBoard(
    private val stage: Stage,
    override val width: Int = 25,
    override val height: Int = 25,
    override val dimensions: Int = 20
) : IGrid {
    fun draw(l: Loc, c: RGBA) = stage.solidRect(
        dimensions.toDouble(),
        dimensions.toDouble(),
        c
    ).xy(l.x * dimensions, l.y * dimensions)

    fun drawBorders() = stage.solidRect(
        width * dimensions.toDouble(),
        height * dimensions.toDouble(),
        Colors.BLACK
    )

    fun locationNotContaining(vararg s: Snake): Loc = locationNotContaining(s.map { it.segments }.flatten())
}