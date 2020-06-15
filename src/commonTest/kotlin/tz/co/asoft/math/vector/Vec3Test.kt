package tz.co.asoft.math.vector

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Vec3Test {
    private val v1 = Vec3(2, 3, 2)
    private val v2 = Vec3(1, -6, 1)

    @Test
    fun `Two vectors are associative`() {
        assertEquals(v1 + v2, v2 + v1)
    }

    @Test
    fun `(2i+3j+2k)+(1i-6j+1k) should equal (3i-3j+3k)`() {
        val v3 = Vec3(3, -3, 3)
        assertEquals(v3, v1 + v2)
    }

    @Test
    fun `(2i+3j+2k)+(1i-6j+1k) should equal (1i+9j+1k)`() {
        assertEquals(Vec(1, 9, 1), v1 - v2)
    }

    @Test
    fun `(2,3,1) dot (1,-6,1) should equal -14`() {
        assertEquals(-14.0, v1 dot v2)
        assertEquals(-14.0, v1 o v2)
    }

    @Test
    fun `(300,400) should have a length of 500`() {
        assertEquals(500.0, Vec(300, 400).length)
    }

    @Test
    fun `normal of parallel vectors are the same`() {
        val v1 = Vec2(3.1, 4.2)
        val v2 = Vec2(6.2, 8.4) * -1
        assertTrue(v1.normalized() == v2.normalized() || v1.normalized() == -v2.normalized())
    }
}