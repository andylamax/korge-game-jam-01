package tz.co.asoft.lab2.entities

import tz.co.asoft.lab2.tools.executeCollision
import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.math.vector.internal.Vec2Impl
import tz.co.asoft.tools.Rect

class Paddle(val pos: Vec2, val halfWidth: Double, val halfHeight: Double) {
    val speed = 300
    val wingWidth = 18
    var inCoolDown = false

    private var motion = Motion.none

    constructor(pos: Vec2, halfWidth: Number, halfHeight: Number) : this(pos, halfWidth.toDouble(), halfHeight.toDouble())

    enum class Motion {
        left, right, none
    }

    val bounds: Rect
        get() {
            val ds = Vec2Impl(halfWidth, halfHeight)
            return Rect(pos - ds, pos + ds)
        }

    fun resetCoolDown() {
        inCoolDown = false
    }

    fun doCollision(b: Ball): Boolean {
        if (!inCoolDown && bounds.overlapsWith(b.bounds)) {
            bounds.executeCollision(b)
            if (motion != Motion.none) {
                b.vel.x *= 1.2
            } else {
                b.vel.x /= 1.2
            }
            inCoolDown = true
            return true
        }
        return false
    }

    fun doCollision(walls: Rect) {
        val rect = bounds
        if (rect.left < walls.left) {
            pos.x += walls.left - rect.left
        } else if (rect.right > walls.right) {
            pos.x -= rect.right - walls.right
        }
    }

    fun update(dt: Double, motion: Motion) {
        this.motion = motion
        when (motion) {
            Motion.left -> pos.x -= speed * dt
            Motion.right -> pos.x += speed * dt
            Motion.none -> pos
        }
    }
}