package football.player.strategy.simple.defense

import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.strategy.AbstractPlayerStrategy

abstract class DefenderStrategy(private val distanceFromGoal: Double) : AbstractPlayerStrategy() {
    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> distanceFromGoal
            AWAY -> FieldContext.fieldTotalWidth - distanceFromGoal
        }
    }

    override fun setInitialY(): Double = FieldContext.fieldHalfHeight
}