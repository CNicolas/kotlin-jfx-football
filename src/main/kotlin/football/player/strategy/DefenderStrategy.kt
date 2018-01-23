package football.player.strategy

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player

abstract class DefenderStrategy(private val distanceFromGoal: Double) : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> distanceFromGoal
            AWAY -> FieldContext.fieldTotalWidth - distanceFromGoal
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight

    protected fun isBallInHalfField(player: Player): Boolean = this.isInHalfField(player.gameSide, Ball.instance.position)
}