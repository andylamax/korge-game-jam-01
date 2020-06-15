package tz.co.asoft.tools

import com.soywiz.klock.TimeSpan
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Stage
import com.soywiz.korge.view.addUpdater

fun Container.gameLoop(loop: Container.(TimeSpan) -> Unit) {
    addUpdater { dt: TimeSpan ->
        removeChildren()
        loop(dt)
    }
}