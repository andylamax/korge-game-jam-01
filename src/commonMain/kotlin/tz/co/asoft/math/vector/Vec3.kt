package tz.co.asoft.math.vector

interface Vec3 : Vec2 {
    var z: Double

    operator fun component3() = z

    override operator fun plus(v: Vec3): Vec3

    override operator fun plus(v: Vec2): Vec3

    operator fun minus(v: Vec3) = this + (v * -1)

    override operator fun minus(v: Vec2): Vec3

    override operator fun unaryMinus() = this * -1

    override operator fun times(n: Number): Vec3

    operator fun times(v: Vec2): Vec3

    operator fun times(v: Vec3): Vec3

    override operator fun div(n: Number) = this * (1 / n.toDouble())

    override fun normalized(): Vec3 = if (lengthSq != 0.0) this / length else this

    override infix fun x(n: Number) = this * n

    infix fun x(v: Vec3) = this * v

    infix fun x(v: Vec2) = this * v

    infix fun dot(v: Vec3) = x * v.x + y * v.y + z * v.z

    infix fun o(v: Vec3) = this dot v
}