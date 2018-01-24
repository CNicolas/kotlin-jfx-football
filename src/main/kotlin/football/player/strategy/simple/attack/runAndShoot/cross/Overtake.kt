package football.player.strategy.simple.attack.runAndShoot.cross

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength.NORMAL
import football.player.ShootingStrength.SHOOT
import football.player.SideInTeam
import football.player.strategy.simple.attack.AttackStrategy
import helpers.Coordinates

class Overtake(override val side: SideInTeam) : AttackStrategy() {
    private val corridorHeight = 50.0

    override fun moveWithoutBall(player: Player): Coordinates = moveTowards(player.position, Ball.instance.position)

    override fun shoot(player: Player): Coordinates {
        return when {
            isInOpponentSurface(player) ->
                shootTowards(getOpponentGoalsCenter(player.gameSide), SHOOT)
            isInLastQuarterOfField(player.gameSide, player.position) ->
                shootTowards(getOpponentGoalsCenter(player.gameSide), NORMAL)
            isInCorridor(player) ->
                shootTowards(continueOnCorridor(player), NORMAL)
            else -> shootTowards(corridorClosestLocation(player), NORMAL)
        }
    }

    private fun continueOnCorridor(player: Player): Coordinates {
        val targetX = when (player.gameSide) {
            GameSide.HOME -> player.position.x + 50.0
            GameSide.AWAY -> player.position.x - 50.0
        }

        return Coordinates(targetX, player.position.y)
    }

    private fun isInCorridor(player: Player): Boolean = when (side) {
        SideInTeam.DOWN -> player.position.y >= FieldContext.fieldTotalHeight - corridorHeight
        else -> player.position.y <= corridorHeight
    }

    private fun corridorClosestLocation(player: Player): Coordinates {
        val bias = when (player.gameSide) {
            GameSide.HOME -> 10
            GameSide.AWAY -> -10
        }

        return when (side) {
            SideInTeam.DOWN -> Coordinates(player.position.x + bias, FieldContext.fieldTotalHeight - corridorHeight)
            else -> Coordinates(player.position.x + bias, corridorHeight)
        }
    }
}