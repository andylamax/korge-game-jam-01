package tz.co.asoft.lab3.scenes

import com.soywiz.korev.Key
import com.soywiz.korge.input.onClick
import com.soywiz.korge.input.onKeyDown
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.lab3.entities.Bird.Companion.bmp
import tz.co.asoft.math.vector.Vec2

class GameMenu(val world: Vec2) : Scene() {
    override suspend fun Container.sceneInit() {
        val img = image(resourcesVfs["flappy.png"].readBitmap()).apply {
            xy((world.x - width) / 2, (world.y - height) / 2)
        }

        text("Press Space to jump. Press Enter to Start Playing", color = Colors.BLACK).apply {
            xy((world.x - width) / 2, img.height / 1.5 + (world.y - height) / 2)
        }

        onKeyDown {
            when (it.key) {
                Key.ENTER -> sceneContainer.changeTo(GameWorldScene::class)
            }
        }
    }
}