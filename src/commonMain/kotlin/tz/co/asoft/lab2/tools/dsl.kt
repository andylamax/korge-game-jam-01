package tz.co.asoft.lab2.tools

import tz.co.asoft.lab2.entities.Ball
import tz.co.asoft.tools.Rect
import kotlin.math.sign

fun Rect.executeCollision(b: Ball) {
    if (sign(b.vel.x) == sign((b.pos - center).x)) {
        b.reboundY()
    } else if (b.pos.x > left && b.pos.x < right) {
        b.reboundY()
    } else {
        b.reboundX()
    }
}