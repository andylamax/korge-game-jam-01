package tz.co.asoft.lab2.entities

import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.tools.Rect

class Ball(var pos: Vec2, var vel: Vec2, val radius: Double = 7.0) {

    val bounds get() = Rect(center = pos, halfWidth = radius, halfHeight = radius)

    fun update(dt: Double) {
        pos += vel * dt
    }

    fun doWallCollision(walls: Rect): Boolean {
        val rect = bounds
        var collided = false
        if (rect.left < walls.left) {
            pos.x += walls.left - rect.left
            collided = true
            reboundX()
        } else if (rect.right > walls.right) {
            pos.x -= rect.right - walls.right
            collided = true
            reboundX()
        }
        if (rect.top < walls.top) {
            pos.y += walls.top - rect.top
            reboundY()
            collided = true
        } else if (rect.bottom > walls.bottom) {
            pos.y -= rect.bottom - walls.bottom
            reboundY()
            collided = true
        }
        return collided
    }

    fun reboundX() {
        vel.x = -vel.x
    }

    /**
     * @param angle is in degrees
     */
    fun reboundY(angle: Double = 90.0) {
        vel.y = -vel.y
    }
}