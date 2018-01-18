package helpers

import java.math.BigInteger

fun distance(from: Coordinates, to: Coordinates): Double {
    return Math.sqrt(Math.pow(to.x - from.x, 2.0) + Math.pow(to.y - from.y, 2.0))
}

fun extractFunctionOfLine(from: Coordinates, to: Coordinates): (Double) -> Double {
    val coefficient = (from.y - to.y) / (from.x - to.x)
    val originOrdinate = from.y - (coefficient * from.x)

    return { x -> coefficient * x + originOrdinate }
}

fun factorial(n: Int): BigInteger = if (n <= 1) BigInteger.ONE else factorial(n - 1) * BigInteger.valueOf(n.toLong())

fun numberOfCombinations(elementsTaken: Int, total: Int): BigInteger =
        factorial(total) / (factorial(elementsTaken) * factorial(total - elementsTaken))

fun numberOfCombinationsToString(elementsTaken: Int, total: Int): String = numberOfCombinations(elementsTaken, total).toString()

operator fun BigInteger.plus(operand: Int): BigInteger {
    return this + BigInteger.valueOf(operand.toLong())
}

operator fun BigInteger.plus(operand: Long): BigInteger {
    return this + BigInteger.valueOf(operand)
}

operator fun BigInteger.plus(operand: Double): BigInteger {
    return this + BigInteger.valueOf(operand.toLong())
}