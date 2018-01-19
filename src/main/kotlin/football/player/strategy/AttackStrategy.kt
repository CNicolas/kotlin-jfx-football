package football.player.strategy

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import helpers.Coordinates
import helpers.distance

abstract class AttackStrategy : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.fieldTotalWidth / 3
            else -> (2 * FieldContext.fieldTotalWidth) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.fieldTotalHeight / 3
            SideInTeam.DOWN -> (2 * FieldContext.fieldTotalHeight) / 3
            else -> FieldContext.fieldHalfHeight
        }
    }

    protected fun isInOpponentSurface(player: Player): Boolean {
        val isInSurfaceByX = isAtSurfaceWidthDistanceOfOpponentGoal(player.team.gameSide, player.position)

        val isInSurfaceByY = player.position.y >= FieldContext.rightSurface.y
                && player.position.y <= FieldContext.rightSurface.height

        return isInSurfaceByX && isInSurfaceByY
    }

    protected fun isAtShootingDistanceOfOpponentGoalCenter(player: Player): Boolean =
            ShootingStrength.SHOOT.distance > distance(Ball.instance.position, getOpponentGoalsCenter(player.gameSide))

    protected fun isAtSurfaceWidthDistanceOfOpponentGoal(gameSide: GameSide, coordinates: Coordinates): Boolean = when (gameSide) {
        GameSide.HOME -> coordinates.x >= FieldContext.rightSurface.x
        GameSide.AWAY -> coordinates.x <= FieldContext.leftSurface.width
    }

    protected fun isInLastQuarterOfField(gameSide: GameSide, coordinates: Coordinates): Boolean = when (gameSide) {
        GameSide.HOME -> coordinates.x >= FieldContext.fieldTotalWidth - FieldContext.fieldTotalWidth / 4
        GameSide.AWAY -> coordinates.x <= FieldContext.fieldTotalWidth / 4
    }

    protected fun isInLastThirdOfField(gameSide: GameSide, coordinates: Coordinates): Boolean = when (gameSide) {
        GameSide.HOME -> coordinates.x >= FieldContext.fieldTotalWidth - FieldContext.fieldTotalWidth / 3
        GameSide.AWAY -> coordinates.x <= FieldContext.fieldTotalWidth / 3
    }
}