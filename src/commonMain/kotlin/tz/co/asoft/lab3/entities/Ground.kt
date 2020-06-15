package tz.co.asoft.lab3.entities

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.image
import com.soywiz.korge.view.xy
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.tools.Rect
import kotlin.math.ceil

class Ground(val rect: Rect) {
    val bmpPath = "ground"
    var bmp: Bitmap? = null

    suspend fun loadResources() {
        bmp = resourcesVfs["flappy/$bmpPath.png"].readBitmap()
    }

    fun draw(stage: Container) {
        bmp?.let {
            repeat(ceil(rect.width / it.width).toInt()) { i ->
                stage.image(it).xy(i * it.width.toDouble(), rect.top)
            }
        }
    }

    fun isCollidingBird(b: Bird) = b.rect.bottom > rect.top

    fun update(dt: TimeSpan) {
        val speed = 200
        rect.left += speed * dt.seconds
        rect.right += speed * dt.seconds
    }
}