package helpers

data class Coordinates(val x: Double, val y: Double) {
    constructor() : this(0.0, 0.0)

    fun clone() = Coordinates(x, y)

    override fun toString(): String {
        return "($x, $y)"
    }
}