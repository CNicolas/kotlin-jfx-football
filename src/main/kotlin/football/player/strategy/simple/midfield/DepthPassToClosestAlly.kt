package football.player.strategy.simple.midfield

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength.*
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.AbstractPlayerStrategy
import helpers.Coordinates
import helpers.distance

class DepthPassToClosestAlly : AbstractPlayerStrategy() {
    override val side: SideInTeam = CENTER

    override fun moveWithoutBall(player: Player): Coordinates = moveTowards(player.position, Ball.instance.position)

    override fun shoot(player: Player): Coordinates {
        val closestAlly = getClosestAlly(player, player.team.getAllies(player.name))

        return when (closestAlly) {
            null -> shootTowards(getOpponentGoalsCenter(player.gameSide), CLEARANCE)
            else -> {
                val distanceToClosestAlly = distance(player.position, closestAlly.position)
                val strength = when {
                    distanceToClosestAlly >= SHOOT.distance * 2 -> CLEARANCE
                    distanceToClosestAlly <= CLEARANCE.distance -> SHOOT
                    else -> CLEARANCE
                }
                val destination = when (player.gameSide) {
                    HOME -> closestAlly.position.copy(x = closestAlly.position.x + RUN.distance)
                    AWAY -> closestAlly.position.copy(x = closestAlly.position.x - RUN.distance)
                }

                shootTowards(destination, strength)
            }
        }
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            HOME -> FieldContext.fieldTotalWidth / 4
            AWAY -> FieldContext.fieldTotalWidth - FieldContext.fieldTotalWidth / 4
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            UP -> FieldContext.fieldTotalHeight / 3
            DOWN -> (2 * FieldContext.fieldTotalHeight) / 3
            CENTER -> FieldContext.fieldHalfHeight
        }
    }

    private fun getClosestAlly(player: Player, allies: List<Player>): Player? {
        return allies.minBy { distance(player.position, it.position) }
    }
}