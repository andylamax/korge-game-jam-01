package tz.co.asoft.lab2

import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.input.onKeyUp
import com.soywiz.korim.color.Colors
import tz.co.asoft.lab2.entities.Paddle
import tz.co.asoft.lab2.korge.draw
import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.tools.Rect
import tz.co.asoft.tools.gameLoop

suspend fun lab2Main() = Korge(title = "Korge Lab", width = 1000, height = 700, bgcolor = Colors["#2b2b2b"]) {
    val game = Game(Rect(topLeft = Vec2(0, 0), bottomRight = Vec2(999, 699)))
    var paddleMotion = Paddle.Motion.none

    onKeyDown {
        paddleMotion = when (it.key) {
            Key.LEFT -> Paddle.Motion.left
            Key.RIGHT -> Paddle.Motion.right
            else -> Paddle.Motion.none
        }
    }

    onKeyUp {
        paddleMotion = Paddle.Motion.none
    }

    gameLoop { dt ->
        game.update(dt.seconds, paddleMotion)
        draw(game)
    }
}