package football.player.strategy.simple.attack.camper

import football.Ball
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.simple.attack.AttackStrategy
import helpers.Coordinates

class InFrontOfBall : AttackStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val toX = when (player.gameSide) {
            HOME -> Ball.instance.position.x + ShootingStrength.SHOOT.distance
            AWAY -> Ball.instance.position.x - ShootingStrength.SHOOT.distance
        }

        return moveTowards(player.position, Coordinates(toX, Ball.instance.position.y))
    }

    override fun shoot(player: Player): Coordinates = shootTowards(player.position, getOpponentGoalsCenter(player.gameSide), ShootingStrength.SHOOT)
}