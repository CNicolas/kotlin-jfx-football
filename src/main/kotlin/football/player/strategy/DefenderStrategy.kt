package football.player.strategy

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player

abstract class DefenderStrategy(private val distanceFromGoal: Double) : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> distanceFromGoal
            else -> FieldContext.fieldTotalWidth - distanceFromGoal
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight

    protected fun isBallInHalfField(player: Player): Boolean = this.isInHalfField(player.team.gameSide, Ball.instance.position)
}