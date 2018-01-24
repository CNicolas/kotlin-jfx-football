package football.player.strategy.simple.attack

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam.*
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import helpers.distance

abstract class AttackStrategy : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> FieldContext.fieldTotalWidth / 3
            AWAY -> (2 * FieldContext.fieldTotalWidth) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            UP -> FieldContext.fieldTotalHeight / 3
            DOWN -> (2 * FieldContext.fieldTotalHeight) / 3
            CENTER -> FieldContext.fieldHalfHeight
        }
    }

    protected fun isInOpponentSurface(player: Player): Boolean {
        val isInSurfaceByX = isAtSurfaceWidthDistanceOfOpponentGoal(player.gameSide, player.position)

        val isInSurfaceByY = player.position.y >= FieldContext.rightSurface.y
                && player.position.y <= FieldContext.rightSurface.height

        return isInSurfaceByX && isInSurfaceByY
    }

    protected fun isAtShootingDistanceOfOpponentGoalCenter(player: Player): Boolean =
            ShootingStrength.SHOOT.distance > distance(Ball.instance.position, getOpponentGoalsCenter(player.gameSide))

    protected fun isAtSurfaceWidthDistanceOfOpponentGoal(gameSide: GameSide, coordinates: Coordinates): Boolean = when (gameSide) {
        HOME -> coordinates.x >= FieldContext.rightSurface.x
        AWAY -> coordinates.x <= FieldContext.leftSurface.width
    }

    protected fun isInLastQuarterOfField(gameSide: GameSide, coordinates: Coordinates): Boolean = when (gameSide) {
        HOME -> coordinates.x >= FieldContext.fieldTotalWidth - FieldContext.fieldTotalWidth / 4
        AWAY -> coordinates.x <= FieldContext.fieldTotalWidth / 4
    }

    protected fun isInLastThirdOfField(gameSide: GameSide, coordinates: Coordinates): Boolean = when (gameSide) {
        HOME -> coordinates.x >= FieldContext.fieldTotalWidth - FieldContext.fieldTotalWidth / 3
        AWAY -> coordinates.x <= FieldContext.fieldTotalWidth / 3
    }
}