package football.player.strategy.simple

import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates

class DoesNothing(override val side: SideInTeam) : AbstractPlayerStrategy() {
    init {
        val x = FieldContext.fieldTotalWidth / 4
        var y = FieldContext.fieldHalfHeight

        if (side == UP) {
            y = FieldContext.fieldTotalHeight / 4
        } else if (side == DOWN) {
            y = (3 * FieldContext.fieldTotalHeight) / 4
        }

        initialPosition = Coordinates(x, y)
    }

    override fun moveWithoutBall(player: Player): Coordinates = player.position

    override fun shoot(player: Player): Coordinates = player.position

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> FieldContext.fieldHalfWidth / 2
            AWAY -> (3 * FieldContext.fieldHalfWidth) / 2
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            UP -> FieldContext.fieldHalfHeight / 2
            CENTER -> FieldContext.fieldHalfHeight
            DOWN -> (3 * FieldContext.fieldTotalHeight) / 4
        }
    }
}