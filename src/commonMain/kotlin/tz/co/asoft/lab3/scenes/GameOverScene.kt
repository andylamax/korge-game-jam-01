package tz.co.asoft.lab3.scenes

import com.soywiz.korev.Key
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.math.vector.Vec
import tz.co.asoft.math.vector.Vec2

class GameOverScene(val score: Int, val world: Vec2) : Scene() {
    override suspend fun Container.sceneInit() {
        val bmp = resourcesVfs["gameover.png"].readBitmap()
        text("Score: $score").apply {
            xy((world.x - width) / 2, bmp.height - (world.y - height) / 2)
        }
        image(bmp).xy((world.x - bmp.width) / 2, (world.y - bmp.height) / 2)
        text("Press Enter key to play again", color = Colors.BLACK).apply {
            xy((world.x - width) / 2, bmp.height + (world.y - height) / 2)
        }
        onKeyDown {
            when (it.key) {
                Key.ENTER -> sceneContainer.changeTo<GameWorldScene>()
            }
        }
    }
}