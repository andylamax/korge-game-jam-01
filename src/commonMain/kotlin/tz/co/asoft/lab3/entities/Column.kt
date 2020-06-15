package tz.co.asoft.lab3.entities

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.image
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.math.vector.Vec
import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.tools.Rect
import tz.co.asoft.tools.korge.fitTo

class Column(var gapPos: Vec2, val vel: Vec2) {
    companion object {
        val gapHeight: Int = 200
        val width: Int = 100
        private var topBmp: Bitmap? = null
        private var bottomBmp: Bitmap? = null

        suspend fun loadResources() {
            topBmp = resourcesVfs["pipetop.png"].readBitmap()
            bottomBmp = resourcesVfs["pipedown.png"].readBitmap()
        }
    }

    val safeZone: Rect
        get() {
            val ds = Vec(width / 2, gapHeight / 2)
            return Rect(gapPos - ds, gapPos + ds)
        }

    fun update(dt: TimeSpan) {
        gapPos += vel * dt.seconds
    }

    fun draw(s: Container, ground: Ground) {
        topBmp?.let {
            val topLeft = Vec(gapPos.x - width / 2, 0)
            val bottomRight = Vec(gapPos.x + width / 2, gapPos.y - gapHeight / 2)
            s.image(it).fitTo(topLeft, bottomRight)
        }

        bottomBmp?.let {
            val topLeft = Vec(gapPos.x - width / 2, gapPos.y + gapHeight / 2)
            val bottomRight = Vec(gapPos.x + width / 2, ground.rect.top)
            s.image(it).fitTo(topLeft, bottomRight)
        }
    }

    fun isCollidingWith(b: Bird): Boolean {
        val bird = b.rect.offseted(-4)
        if (bird.right > gapPos.x - width / 2 && bird.right < gapPos.x + width / 2) {
            if (bird.top < safeZone.top) {
                return true
            } else if (bird.bottom > safeZone.bottom) {
                return true
            }
        }
        return false
    }
}