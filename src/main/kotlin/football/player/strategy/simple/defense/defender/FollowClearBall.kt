package football.player.strategy.simple.defense.defender

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.ShootingStrength.CLEARANCE
import football.player.SideInTeam
import football.player.strategy.simple.defense.DefenderStrategy
import helpers.Coordinates

class FollowClearBall(distanceFromGoal: Double = FieldContext.fieldTotalWidth / 6) : DefenderStrategy(distanceFromGoal) {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = when {
            isInHalfField(player.gameSide, Ball.instance.position) -> Ball.instance.position
            else -> Coordinates(initialPosition.x, Ball.instance.position.y)
        }

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates = shootTowards(getOpponentGoalsCenter(player.gameSide), CLEARANCE)
}