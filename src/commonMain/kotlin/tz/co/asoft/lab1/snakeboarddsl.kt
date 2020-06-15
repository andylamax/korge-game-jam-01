package tz.co.asoft.lab1

import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA

fun SnakeBoard.draw(s: Snake, bodyColor: RGBA = Colors.GREEN) {
    s.body.forEach { draw(it, bodyColor.withA((100..255).random())) }
    draw(s.head, s.headColor)
}

fun SnakeBoard.draw(f: Fruit, color: RGBA = Colors.RED) = draw(f.loc, color)

fun Snake.inside(b: SnakeBoard) = head.x in 0 until b.width && head.y in 0 until b.height

fun Snake.hasEaten(f: Fruit) = head == f.loc

fun Snake.update(b: SnakeBoard) {
    if (!inside(b) || headInBody) {
        isDead = true
    } else {
        move()
    }
}