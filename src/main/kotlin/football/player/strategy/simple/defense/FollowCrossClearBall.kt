package football.player.strategy.simple.defense

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import helpers.Coordinates
import java.util.*

class FollowCrossClearBall(distanceFromGoal: Double = FieldContext.fieldTotalWidth / 6) : DefenderStrategy(distanceFromGoal) {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = when {
            isBallInHalfField(player) -> Ball.instance.position
            else -> Coordinates(initialPosition.x, Ball.instance.position.y)
        }

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val r = Random()
        val variation = when {
            r.nextBoolean() -> FieldContext.fieldHalfHeight / 3
            else -> -FieldContext.fieldHalfHeight / 3
        }
        val destination = getOpponentGoalsCenter(player.gameSide)

        return shootTowards(player.position, destination.copy(y = destination.y + variation), ShootingStrength.CLEARANCE)
    }
}