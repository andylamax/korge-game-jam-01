package tz.co.asoft.lab1

import tz.co.asoft.tools.Loc

enum class Delta(val loc: Loc) {
    Right(Loc(1, 0)),
    Left(Loc(-1, 0)),
    Up(Loc(0, -1)),
    Down(Loc(0, 1))
}