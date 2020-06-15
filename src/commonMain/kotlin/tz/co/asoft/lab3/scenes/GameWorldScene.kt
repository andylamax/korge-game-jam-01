package tz.co.asoft.lab3.scenes

import com.soywiz.korev.Key
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korio.async.launch
import tz.co.asoft.lab3.Game
import tz.co.asoft.tools.gameLoop

class GameWorldScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        onKeyDown {
            if (it.key == Key.SPACE) {
                game.bird.jump()
            } else if (it.key == Key.UP) {
                game.bird.jump()
            }
        }

        game.onGameEnded = { g, _ ->
            launch {
                sceneContainer.changeTo<GameOverScene>(g.score)
            }
        }

        onClick {
            game.bird.jump()
        }

        gameLoop {
            if (!game.isOver) {
                game.update(it)
                game.draw(this)
            }
        }
    }
}