package helpers

import football.Ball
import football.FieldContext
import football.FieldContext.Companion.cageWidth
import football.FieldContext.Companion.fieldHalfHeight
import football.FieldContext.Companion.fieldTotalWidth
import football.FieldContext.Companion.maxDistanceToTouch
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength

fun getMaxCoordinates(from: Coordinates, aim: Coordinates, maxDistance: Double): Coordinates {
    var toX = from.x
    var toY = from.y

    for (count in 0 until 1000) {
        val currentDistanceTowardsObjective = distance(from, Coordinates(toX, toY))

        val isArrived = Math.abs(maxDistance - currentDistanceTowardsObjective) < 1 || Coordinates(toX, toY) == aim
        if (isArrived) {
            return Coordinates(toX, toY)
        }

        if (toX < aim.x) toX += 1
        if (toX > aim.x) toX -= 1
        if (toY < aim.y) toY += 1
        if (toY > aim.y) toY -= 1
    }

    return Coordinates(toX, toY)
}

fun hasBall(player: Player): Boolean {
    val diffX = Math.abs(player.position.x - Ball.instance.position.x)
    val diffY = Math.abs(player.position.y - Ball.instance.position.y)

    return diffX < maxDistanceToTouch && diffY < maxDistanceToTouch
}

fun doesBallEnterCage(futureBallPosition: Coordinates): Any {
    val linearFunction = extractFunctionOfLine(Ball.instance.position, futureBallPosition)

    val beforeHomeLine = futureBallPosition.x < 0
    val afterAwayLine = futureBallPosition.x > fieldTotalWidth

    return when {
        beforeHomeLine -> if (crossLineInCage(HOME, linearFunction)) HOME else false
        afterAwayLine -> if (crossLineInCage(AWAY, linearFunction)) AWAY else false
        else -> false
    }
}

fun isThereAPlayerOnBallsWay(futureBallPosition: Coordinates, players: List<Player>): Any {
    val linearFunction = extractFunctionOfLine(Ball.instance.position, futureBallPosition)

    for (i in 0 until players.size) {
        val player = players[i]

        val distanceCrossedByBall = distance(Ball.instance.position, futureBallPosition)
        val isClearance = Math.abs(distanceCrossedByBall - (ShootingStrength.CLEARANCE.distance)) < FieldContext.shootingDistance
        val ballLineYForPlayer = linearFunction(player.position.x)

        val playerIsSomewhereOnTheBallsWay = Math.abs(ballLineYForPlayer - player.position.y) < maxDistanceToTouch

        if (playerIsSomewhereOnTheBallsWay
                && !isClearance
                && distance(player.position, futureBallPosition) <= distanceCrossedByBall) {
            return player.position
        }
    }

    return false
}

private fun crossLineInCage(side: GameSide, linearFunction: (Double) -> Double): Boolean {
    val yWhenCrossLine = when (side) {
        HOME -> linearFunction(0.0)
        AWAY -> linearFunction(fieldTotalWidth)
    }

    return yWhenCrossLine > fieldHalfHeight - cageWidth && yWhenCrossLine < fieldHalfHeight + cageWidth
}