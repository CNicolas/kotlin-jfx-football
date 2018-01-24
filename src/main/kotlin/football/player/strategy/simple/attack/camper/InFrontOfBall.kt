package football.player.strategy.simple.attack.camper

import football.Ball
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength.SHOOT
import football.player.SideInTeam
import football.player.strategy.simple.attack.AttackStrategy
import helpers.Coordinates

class InFrontOfBall : AttackStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val toX = when (player.gameSide) {
            HOME -> Ball.instance.position.x + SHOOT.distance
            AWAY -> Ball.instance.position.x - SHOOT.distance
        }

        return moveTowards(player.position, Coordinates(toX, Ball.instance.position.y))
    }

    override fun shoot(player: Player): Coordinates = shootTowards(getOpponentGoalsCenter(player.gameSide), SHOOT)
}