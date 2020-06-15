package tz.co.asoft.lab1

import com.soywiz.korev.Key
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy

class SnakeGame1(private val stage: Stage, val level: Int = 1) : Game() {
    val board = SnakeBoard(stage)
    var snake = Snake(0, 0, Delta.Right)
    var fruit = Fruit(board.locationNotContaining(snake))
    val gameIsOver get() = snake.isDead

    val intensity get() = (level.toDouble() - 1) / level

    init {
        stage.onKeyDown {
            snake.delta = when (it.key) {
                Key.UP -> Delta.Up
                Key.DOWN -> Delta.Down
                Key.LEFT -> Delta.Left
                Key.RIGHT -> Delta.Right
                Key.ENTER -> {
                    start()
                    Delta.Right
                }
                else -> snake.delta
            }
        }
    }

    fun start() {
        snake = Snake(0, 0, Delta.Right)
        fruit = Fruit(board.locationNotContaining(snake))
    }

    override fun update() {
        if (!gameIsOver) {
            if (snake.isAlive) {
                snake.snakeRate++
            }
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

    fun drawGameOver() {
        stage.text("Game Over Baby").xy(200, 200)
        stage.text("Score: ${snake.segments.size - 1}")
    }

    override fun draw() {
        stage.removeChildren()
        if (gameIsOver) {
            drawGameOver()
        } else {
            board.drawBorders()
            stage.text("Score: ${((snake.segments.size - 1) * (1 + intensity)).toInt()}")
            board.draw(snake)
            board.draw(fruit)
        }
    }
}