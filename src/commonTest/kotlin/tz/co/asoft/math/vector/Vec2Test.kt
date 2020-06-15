package tz.co.asoft.math.vector

import com.soywiz.kmem.isAlmostZero
import kotlin.math.acos
import kotlin.math.asin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Vec2Test {
    private val v1 = Vec2(2, 3)
    private val v2 = Vec2(1, -6)

    @Test
    fun `Two vectors are associative`() {
        assertEquals(v1 + v2, v2 + v1)
    }

    @Test
    fun `(2i+3j)+(1i-6j) should equal(3i-3j)`() {
        val v3 = Vec2(3, -3)
        assertEquals(v3, v1 + v2)
    }

    @Test
    fun `(2i+3j)-(1i-6j) should equal(1i+9j)`() {
        assertEquals(Vec2(1, 9), v1 - v2)
    }

    @Test
    fun `(2,3) dot (1,-6) should equal -16`() {
        assertEquals(-16.0, v1 dot v2)
        assertEquals(-16.0, v1 o v2)
    }

    @Test
    fun `(300,400) should have a length of 500`() {
        assertEquals(500.0, Vec2(300, 400).length)
    }

    @Test
    fun `normal of parallel vectors are the same`() {
        val v1 = 3.1 * i + 4.2 * j
        val v2 = Vec2(6.2, 8.4) * -1
        assertTrue(v1.normalized() == v2.normalized() || v1.normalized() == -v2.normalized())
    }
}