package tz.co.asoft.lab1

import com.soywiz.klock.timesPerSecond
import com.soywiz.korev.Key
import com.soywiz.korge.Korge
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.view.addFixedUpdater
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors

suspend fun lab1Main() = Korge(title = "Korge Lab", width = 500, height = 500, bgcolor = Colors["#2b2b2b"]) {
    var game: Game? = null

    text("Single Player: Press 1\nMulti Player: Press 2").xy(100, 200)

    onKeyDown {
        when (it.key) {
            Key.N1 -> {
                game = SnakeGame1(this)
            }
            Key.N2 -> {
                game = SnakeGame2(this)
            }
        }
    }

    addFixedUpdater(60.timesPerSecond) {
        game?.apply {
            update()
            draw()
        }
    }
}