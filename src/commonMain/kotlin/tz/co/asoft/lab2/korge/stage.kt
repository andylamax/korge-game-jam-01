package tz.co.asoft.lab2.korge

import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.circle
import com.soywiz.korge.view.solidRect
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import tz.co.asoft.lab2.Game
import tz.co.asoft.lab2.entities.Ball
import tz.co.asoft.lab2.entities.Brick
import tz.co.asoft.lab2.entities.Paddle
import tz.co.asoft.tools.Rect

fun Stage.draw(r: Rect, color: RGBA) = solidRect(r.width, r.height, color).xy(r.left, r.top)

fun Stage.draw(b: Brick) = draw(b.rect.offseted(-b.padding), b.color)

fun Stage.draw(b: Ball) = circle(b.radius, color = Colors.YELLOW).xy(b.pos.x, b.pos.y)

fun Stage.draw(p: Paddle) {
    val wings = p.bounds
    val noWings = wings.copy(left = wings.left + p.wingWidth, right = wings.right - p.wingWidth)
    draw(wings, Colors.RED)
    draw(noWings, Colors.WHITE)
}

fun Stage.draw(g: Game) {
    draw(g.walls, Colors.BLACK)
    g.bricks.forEach { draw(it) }
    draw(g.ball)
    draw(g.paddle)
}

