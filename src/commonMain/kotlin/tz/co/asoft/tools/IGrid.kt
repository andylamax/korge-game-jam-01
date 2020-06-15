package tz.co.asoft.tools

interface IGrid {
    val width: Int
    val height: Int
    val dimensions: Int

    fun locationNotContaining(locs: List<Loc>): Loc {
        val loc = Loc((0..width).random(), (0..height).random())
        if (locs.any { it == loc }) {
            return locationNotContaining(locs)
        }
        return loc
    }
}