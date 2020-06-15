package tz.co.asoft.math.complex

import tz.co.asoft.math.complex.Cplx.Companion.i
import tz.co.asoft.math.complex.Cplx.Companion.one
import kotlin.test.Test
import kotlin.test.assertEquals

class CplxTest {
    @Test
    fun `Complex Numbers are instantiated properly`() {
        val z1 = 2 + 3 * i
        assertEquals(Cplx(2, 3), z1)
        assertEquals(-one, i * i)
        assertEquals(one, i.pow(0))
        assertEquals(i, i.pow(1))
        assertEquals(-one, i.pow(2))
        assertEquals(-i, i.pow(-1))
        assertEquals(-2 * i, 2 / i)
        assertEquals(-one, i.pow(-2))
        assertEquals(2 * one, Cplx(4, 0).pow(0.5))
        println((1 + 2 * i).pow(2))
        println("sqrt(-3 + 4i) = ${((-3 + 4 * i).pow(0.5))}")
    }
}