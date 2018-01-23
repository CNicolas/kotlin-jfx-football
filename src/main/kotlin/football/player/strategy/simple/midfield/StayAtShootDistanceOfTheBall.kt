package football.player.strategy.simple.midfield

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import helpers.Coordinates
import helpers.getMaxCoordinates

class StayAtShootDistanceOfTheBall(distanceFromGoal: Double = FieldContext.fieldTotalWidth / 4) : DefenderStrategy(distanceFromGoal) {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val distanceOfTheBall = ShootingStrength.SHOOT.distance
        val toX = when (player.gameSide) {
            HOME -> Math.max(Ball.instance.position.x - distanceOfTheBall, FieldContext.surfaceHeight)
            AWAY -> Math.min(Ball.instance.position.x + distanceOfTheBall, FieldContext.fieldTotalWidth)
        }

        val destination = getMaxCoordinates(Ball.instance.position, Coordinates(toX, Ball.instance.position.y), distanceOfTheBall)

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player.gameSide)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromMiddle = FieldContext.fieldTotalWidth / 4

        return when (gameSide) {
            HOME -> FieldContext.fieldHalfWidth - distanceFromMiddle
            else -> FieldContext.fieldHalfWidth + distanceFromMiddle
        }
    }
}
