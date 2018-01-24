package football.player.strategy.combined.quarters

import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.strategy.AbstractPlayerStrategy
import football.player.strategy.PlayerStrategy
import helpers.Coordinates

abstract class AbstractCombinedQuartersStrategy : AbstractPlayerStrategy(), CombinedQuartersStrategy {
    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)
        defenseStrategy.initialPosition = initialPosition
        midDefenseStrategy.initialPosition = initialPosition
        midAttackStrategy.initialPosition = initialPosition
        attackStrategy.initialPosition = initialPosition

        return initialPosition
    }

    override fun setInitialX(gameSide: GameSide): Double = midDefenseStrategy.setInitialX(gameSide)

    override fun setInitialY(): Double = midDefenseStrategy.setInitialY()

    override fun moveWithoutBall(player: Player): Coordinates = chooseStrategy(player).moveWithoutBall(player)
    override fun shoot(player: Player): Coordinates = chooseStrategy(player).shoot(player)

    private fun chooseStrategy(player: Player): PlayerStrategy {
        return when (player.gameSide) {
            HOME -> when {
                player.position.x < FieldContext.fieldTotalWidth / 4 -> defenseStrategy
                player.position.x < FieldContext.fieldHalfWidth -> midDefenseStrategy
                player.position.x < FieldContext.fieldHalfWidth + FieldContext.fieldTotalWidth / 4 -> midAttackStrategy
                player.position.x < FieldContext.fieldTotalWidth -> attackStrategy
                else -> midDefenseStrategy
            }
            AWAY -> when {
                player.position.x < FieldContext.fieldTotalWidth / 4 -> attackStrategy
                player.position.x < FieldContext.fieldHalfWidth -> midAttackStrategy
                player.position.x < FieldContext.fieldHalfWidth + FieldContext.fieldTotalWidth / 4 -> midDefenseStrategy
                player.position.x < FieldContext.fieldTotalWidth -> defenseStrategy
                else -> midDefenseStrategy
            }
        }
    }
}