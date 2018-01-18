package football.player.strategy.simple.attack.camper

import football.Ball
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.AttackStrategy
import helpers.Coordinates
import helpers.distance

class FollowBallHorizontally : AttackStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        return when {
            distance(player.position, Ball.instance.position) < ShootingStrength.RUN.distance -> moveTowards(player.position, Ball.instance.position)
            else -> {
                val destination = Coordinates(Ball.instance.position.x, initialPosition.y)
                moveTowards(player.position, destination)
            }
        }
    }

    override fun shoot(player: Player): Coordinates = shootTowards(player.position, getOpponentGoalsCenter(player), ShootingStrength.SHOOT)
}