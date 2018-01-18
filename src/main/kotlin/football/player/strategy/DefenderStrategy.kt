package football.player.strategy

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player

abstract class DefenderStrategy : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        val distanceFromCage = FieldContext.fieldTotalWidth / 5

        return when (gameSide) {
            GameSide.HOME -> distanceFromCage
            else -> FieldContext.fieldTotalWidth - distanceFromCage
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight

    protected fun isBallInHalfField(player: Player): Boolean = this.isInTeamHalfField(player.team.gameSide, Ball.instance.position)
}