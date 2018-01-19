package football.player.strategy.simple.attack.camper

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AttackStrategy
import helpers.Coordinates

class CampInOpponentSurface : AttackStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        return if (isAtShootingDistance(player, Ball.instance.position)
                && isInOpponentHalfField(player.team.gameSide, Ball.instance.position)) {
            moveTowards(player.position, Ball.instance.position)
        } else {
            val opponentGoalCenter = getOpponentGoalsCenter(player.team.gameSide)
            val destination = when (player.team.gameSide) {
                GameSide.HOME -> opponentGoalCenter.copy(x = opponentGoalCenter.x - FieldContext.surfaceWidth)
                GameSide.AWAY -> opponentGoalCenter.copy(x = opponentGoalCenter.x + FieldContext.surfaceWidth)
            }

            moveTowards(player.position, destination)
        }
    }

    override fun shoot(player: Player): Coordinates =
            shootTowards(player.position, getOpponentGoalsCenter(player.gameSide), ShootingStrength.SHOOT)
}