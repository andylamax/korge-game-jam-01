package tz.co.asoft.lab3.entities

import com.soywiz.klock.TimeSpan
import com.soywiz.korau.sound.NativeSound
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.math.vector.*
import tz.co.asoft.tools.Rect
import tz.co.asoft.tools.korge.fitTo

class Bird(var pos: Vec2, var vel: Vec2) {
    companion object {
        val bmpPath = "bird"
        var bmp: Bitmap? = null
        var flapSound: NativeSound? = null
        var hitSound: NativeSound? = null
        var pointSound: NativeSound? = null
    }

    val hurtBox = 40.0
    val rect get() = Rect(center = pos, halfWidth = hurtBox / 2, halfHeight = hurtBox / 2)
    val dv = Vec(y = -1500)

    val g = 3000 * j
    suspend fun loadResources() {
        bmp = resourcesVfs["$bmpPath.png"].readBitmap()
        flapSound = resourcesVfs["wing.wav"].readSound()
        hitSound = resourcesVfs["hit.wav"].readSound()
        pointSound = resourcesVfs["point.wav"].readSound()
    }

    fun jump() {
        vel += dv * if (vel.y < 0) 0.5 else 1
        flapSound?.play()
    }

    fun update(dt: TimeSpan) {
        vel += g * dt.seconds
        vel *= 0.93
        pos += vel * dt.seconds
    }

    fun draw(s: Container) {
        bmp?.let {
            s.image(it).fitTo(rect)
        }
    }
}