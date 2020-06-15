package tz.co.asoft.lab1

import com.soywiz.korev.Key
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors

class SnakeGame2(private val stage: Stage, val level: Int = 1) : Game() {
    val board = SnakeBoard(stage)
    var snakes = listOf(
        Snake(0, 0, Delta.Right, headColor = Colors.YELLOW),
        Snake(24, 24, Delta.Left, headColor = Colors.BLUE)
    )
    var fruit = Fruit(board.locationNotContaining(*snakes.toTypedArray()))
    val gameIsOver get() = snakes.all { it.isDead }

    val intensity get() = (level.toDouble() - 1) / level

    init {
        stage.onKeyDown {
            if (it.key == Key.ENTER) {
                start()
                Delta.Right
            }
            snakes[1].delta = when (it.key) {
                Key.UP -> Delta.Up
                Key.DOWN -> Delta.Down
                Key.LEFT -> Delta.Left
                Key.RIGHT -> Delta.Right
                else -> snakes[1].delta
            }

            snakes[0].delta = when (it.key) {
                Key.W -> Delta.Up
                Key.S -> Delta.Down
                Key.A -> Delta.Left
                Key.D -> Delta.Right
                else -> snakes[0].delta
            }
        }
    }

    fun start() {
        snakes = listOf(
            Snake(0, 0, Delta.Right, headColor = Colors.YELLOW),
            Snake(24, 24, Delta.Left, headColor = Colors.BLUE)
        )
        fruit = Fruit(board.locationNotContaining(*snakes.toTypedArray()))
    }

    override fun update() {
        if (!gameIsOver) {
            snakes.filter { it.isAlive }.forEach { snake ->
                snake.snakeRate++
                if (snake.snakeRate >= Snake.SNAKE_RATE) {
                    snake.snakeRate = 0
                    snake.update(board)
                    if (snake.hasEaten(fruit)) {
                        snake.grow()
                        fruit = Fruit(board.locationNotContaining(snake))
                    }
                }
            }
        }
    }

    fun drawGameOver() {
        stage.text("Game Over Baby").xy(200, 200)
    }

    override fun draw() {
        stage.removeChildren()
        if (gameIsOver) {
            drawGameOver()
        } else {
            board.drawBorders()
            snakes.filter {
                it.isAlive
            }.forEach { board.draw(it) }
            board.draw(fruit)
        }
    }
}