package tz.co.asoft.lab3

import com.soywiz.korge.scene.Module
import com.soywiz.korim.color.Colors
import com.soywiz.korinject.AsyncInjector
import tz.co.asoft.lab3.scenes.GameMenu
import tz.co.asoft.lab3.scenes.GameOverScene
import tz.co.asoft.lab3.scenes.GameWorldScene
import tz.co.asoft.math.vector.Vec

object FlappyModule : Module() {
    override val mainScene = GameMenu::class
    override val bgcolor = Colors.AQUA

    val world get() = Vec(windowSize.width.toDouble(), windowSize.height.toDouble())
    override suspend fun AsyncInjector.configure() {
        mapPrototype { GameMenu(world) }
        mapPrototype {
            val game = Game(world.x, world.y) { _, _ -> }
            game.loadResources()
            GameWorldScene(game)
        }
        mapPrototype { GameOverScene(get(), world) }
    }
}