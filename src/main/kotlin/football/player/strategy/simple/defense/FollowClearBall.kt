package football.player.strategy.simple.defense

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import helpers.Coordinates

class FollowClearBall(distanceFromGoal: Double = FieldContext.surfaceWidth / 5) : DefenderStrategy(distanceFromGoal) {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = when {
            isBallInHalfField(player) -> Ball.instance.position
            else -> Coordinates(initialPosition.x, Ball.instance.position.y)
        }

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        return shootTowards(player.position, getOpponentGoalsCenter(player), ShootingStrength.CLEARANCE)
    }
}