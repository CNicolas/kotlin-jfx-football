package football.player.strategy.simple.defense

import football.Ball
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import helpers.Coordinates

class DefenderFollowingBall : DefenderStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = Coordinates(initialPosition.x, Ball.instance.position.y)

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player)

        return shootTowards(player.position, destination, ShootingStrength.CLEARANCE)
    }
}