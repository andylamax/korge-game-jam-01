package tz.co.asoft.lab2.korge

import com.soywiz.korau.sound.PlaybackParameters
import com.soywiz.korau.sound.readSound
import com.soywiz.korio.file.std.resourcesVfs
import tz.co.asoft.tools.Sound

suspend fun Sound.play() {
    resourcesVfs["Sounds/$path.wav"].readSound().play(PlaybackParameters(volume = 0.01))
}