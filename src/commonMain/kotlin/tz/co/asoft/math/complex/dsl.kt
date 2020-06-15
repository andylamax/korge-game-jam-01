package tz.co.asoft.math.complex

import tz.co.asoft.math.complex.Cplx.Companion.one

operator fun Number.div(c: Cplx) = (this * one) / c

operator fun Number.times(c: Cplx) = c * this

operator fun Number.plus(c: Cplx) = this * one + c