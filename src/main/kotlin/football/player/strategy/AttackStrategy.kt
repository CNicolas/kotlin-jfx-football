package football.player.strategy

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
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
        val isInSurfaceByX = isAtSurfaceWidthDistanceOfOpponentGoal(player)

        val isInSurfaceByY = player.position.y >= FieldContext.rightSurface.y
                && player.position.y <= FieldContext.rightSurface.height

        return isInSurfaceByX && isInSurfaceByY
    }

    protected fun isAtShootingDistanceOfOpponentGoalCenter(player: Player): Boolean =
            ShootingStrength.SHOOT.distance > distance(Ball.instance.position, getOpponentGoalsCenter(player))

    protected fun isAtSurfaceWidthDistanceOfOpponentGoal(player: Player): Boolean = when (player.team.gameSide) {
        GameSide.HOME -> player.position.x >= FieldContext.rightSurface.x
        GameSide.AWAY -> player.position.x <= FieldContext.leftSurface.width
    }

    protected fun isInLastQuarterOfField(player: Player): Boolean = when (player.team.gameSide) {
        GameSide.HOME -> player.position.x >= FieldContext.fieldTotalWidth - FieldContext.fieldTotalWidth / 4
        GameSide.AWAY -> player.position.x <= FieldContext.fieldTotalWidth / 4
    }
}