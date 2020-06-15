package tz.co.asoft.lab2.entities

import com.soywiz.korim.color.RGBA
import tz.co.asoft.lab2.tools.executeCollision
import tz.co.asoft.tools.Rect

class Brick(val rect: Rect, val color: RGBA) {
    var destroyed = false
    val padding = 1
    fun checkCollision(b: Ball) = rect.overlapsWith(b.bounds)
    fun executeCollision(b: Ball) {
        require(checkCollision(b))
        rect.executeCollision(b)
        destroyed = true
    }
}