package football.player.strategy.simple.attack.runAndShoot.cross

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AttackStrategy
import helpers.Coordinates

class Overtake(override val side: SideInTeam) : AttackStrategy() {
    private val corridorHeight = 50.0

    override fun moveWithoutBall(player: Player): Coordinates = moveTowards(player.position, Ball.instance.position)

    override fun shoot(player: Player): Coordinates {
        return when {
            isInOpponentSurface(player) ->
                shootTowards(player.position, getOpponentGoalsCenter(player.gameSide), ShootingStrength.SHOOT)
            isInLastQuarterOfField(player.team.gameSide, player.position) ->
                shootTowards(player.position, getOpponentGoalsCenter(player.gameSide), ShootingStrength.NORMAL)
            isInCorridor(player) ->
                shootTowards(player.position, continueOnCorridor(player), ShootingStrength.NORMAL)
            else -> shootTowards(player.position, corridorClosestLocation(player), ShootingStrength.NORMAL)
        }
    }

    private fun continueOnCorridor(player: Player): Coordinates {
        val targetX = when (player.team.gameSide) {
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
        val bias = when (player.team.gameSide) {
            GameSide.HOME -> 10
            GameSide.AWAY -> -10
        }

        return when (side) {
            SideInTeam.DOWN -> Coordinates(player.position.x + bias, FieldContext.fieldTotalHeight - corridorHeight)
            else -> Coordinates(player.position.x + bias, corridorHeight)
        }
    }
}