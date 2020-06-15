package tz.co.asoft.tools.korge

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.scale
import com.soywiz.korge.view.xy
import tz.co.asoft.math.vector.Vec2
import tz.co.asoft.tools.Rect

fun Image.scaleTo(width: Double, height: Double): Image {
    val iw = this.width
    val ih = this.height
    return scale(width / iw, height / ih)
}

fun Image.fitTo(topLeft: Vec2, bottomRight: Vec2): Image {
    val size = bottomRight - topLeft
    return scaleTo(size.x, size.y).xy(topLeft.x, topLeft.y)
}

fun Image.fitTo(r: Rect) = fitTo(r.topLeft, r.bottomRight)