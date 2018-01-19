package football.player.strategy.simple.attack.runAndShoot.shootStraight

import football.Ball
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AttackStrategy
import helpers.Coordinates

class PushBallAndShootStraight(override val side: SideInTeam) : AttackStrategy() {
    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player.gameSide)
        val strength = when (isInOpponentSurface(player)) {
            true -> ShootingStrength.SHOOT
            false -> ShootingStrength.NORMAL
        }

        return shootTowards(player.position, destination, strength)
    }
}