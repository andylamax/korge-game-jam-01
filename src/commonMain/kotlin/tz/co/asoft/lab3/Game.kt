package tz.co.asoft.lab3

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.image
import com.soywiz.korge.view.text
import com.soywiz.korge.view.xy
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.bitmap.BitmapSliceCompat
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.lab3.entities.Bird
import tz.co.asoft.lab3.entities.Column
import tz.co.asoft.lab3.entities.Ground
import tz.co.asoft.math.vector.Vec
import tz.co.asoft.tools.Rect
import tz.co.asoft.tools.korge.fitTo

class Game(val width: Double, val height: Double, var onGameEnded: (Game, Int) -> Unit) {
    companion object {
        var bg: Bitmap? = null
    }

    val groundHeight = 100
    val ground = Ground(Rect(Vec(0, height - groundHeight), Vec(width, height)))
    val bird = Bird(Vec(200, 200), Vec())
    var columnSpeed = Vec(-400)
    val newYPos get() = (100..(ground.rect.top - Column.gapHeight).toInt()).random()
    var score = 0
    var isOver = false
        set(value) {
            if (value) {
                onGameEnded(this, 100)
            }
            field = value
        }
    var columns = mutableListOf(Column(Vec(width * 1, height / 2), columnSpeed))
        get() {
            val ogList = field
            field = field.filter { it.gapPos.x >= -Column.width }.toMutableList()
            if (ogList.size != field.size) {
                score++
                Bird.pointSound?.play()
                field.add(Column(Vec(width * 1.5, newYPos), columnSpeed))
            }
            return field
        }

    init {
        columns.add(Column(Vec(width * 1.5, newYPos), columnSpeed))
        columns.add(Column(Vec(width * 2, newYPos), columnSpeed))
    }

    suspend fun loadResources() {
        Column.loadResources()
        ground.loadResources()
        bird.loadResources()
        bg = resourcesVfs["bg.png"].readBitmap()
    }

    fun update(dt: TimeSpan) {
        if (!isOver) {
            ground.update(dt)
            if (ground.isCollidingBird(bird)) {
                isOver = true
                Bird.hitSound?.play()
                return
            }
            columns.forEach {
                it.update(dt)
                if (it.isCollidingWith(bird)) {
                    isOver = true
                    Bird.hitSound?.play()
                    return
                }
            }
            bird.update(dt)
        }
    }

    private fun drawScore(s: Container) {
        s.text("Score: $score", color = Colors.BLACK).xy(0, 0)
    }

    fun draw(s: Container) {
        bg?.let { s.image(it).fitTo(Rect(0, width, 0, height)) }
        bird.draw(s)
        ground.draw(s)
        columns.forEach { it.draw(s, ground) }
        drawScore(s)
    }
}