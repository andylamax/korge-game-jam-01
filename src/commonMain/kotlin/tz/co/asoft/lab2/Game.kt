package tz.co.asoft.lab2

import com.soywiz.korim.color.Colors
import com.soywiz.korio.async.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import tz.co.asoft.lab2.entities.Ball
import tz.co.asoft.lab2.entities.Brick
import tz.co.asoft.lab2.entities.Paddle
import tz.co.asoft.lab2.korge.play
import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.math.vector.internal.Vec2Impl
import tz.co.asoft.tools.Rect
import tz.co.asoft.tools.Sound
import kotlin.math.min

class Game(val walls: Rect) {
    companion object {
        val BRICK_SIZE = Vec2(40, 24)
        const val BRICKS_ACROSS = 21
        const val BRICKS_DOWN = 6
    }

    var bricks = mutableListOf<Brick>()
        get() {
            field = field.filter { !it.destroyed }.toMutableList()
            return field
        }
    val paddle = Paddle(Vec2(500, 600), 50, 15)
    val ball = Ball(paddle.pos + Vec2(0, -100), Vec2(-200, -200))
    val scope = CoroutineScope(SupervisorJob())

    init {
        val allowedColors = Colors.colorsByName.values - Colors.BLACK
        val colors = List(BRICKS_DOWN) { allowedColors.random() }
        val padding = walls.width - BRICK_SIZE.x * BRICKS_ACROSS
        val topLeft = Vec2Impl(padding / 2, padding / 2)
        for (y in 0 until BRICKS_DOWN) {
            val color = colors[y]
            for (x in 0 until BRICKS_ACROSS) {
                val rCord = topLeft + Vec2Impl(x * BRICK_SIZE.x, y * BRICK_SIZE.y)
                val brick = Brick(Rect(rCord, BRICK_SIZE.x, BRICK_SIZE.y), color)
                bricks.add(brick)
            }
        }
    }

    object sound {
        val arkpad = Sound("arkpad")
        val arkbrick = Sound("arkbrick")
        val fart = Sound("fart")
        val ready = Sound("ready")
    }

    fun update(dt: Double, motion: Paddle.Motion) {
        var elapsedTime = dt
        while (elapsedTime > 0) {
            val duration = min(0.0025, elapsedTime)
            ball.update(duration)
            paddle.update(duration, motion)
            paddle.doCollision(walls)
            if (paddle.doCollision(ball)) {
                scope.launch { }
            }
            if (ball.doWallCollision(walls)) {
                paddle.resetCoolDown()
                scope.launch { sound.arkpad.play() }
            }

            bricks.filter {
                it.checkCollision(ball)
            }.minBy {
                (it.rect.center - ball.pos).lengthSq
            }?.let {
                it.executeCollision(ball)
                paddle.resetCoolDown()
                scope.launch { sound.arkbrick.play() }
            }
            elapsedTime -= duration
        }
    }
}