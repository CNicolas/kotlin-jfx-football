package football.player.strategy.simple.defense.goal

import football.FieldContext
import football.player.Player
import football.player.ShootingStrength.CLEARANCE
import football.player.SideInTeam
import football.player.strategy.simple.defense.DefenderStrategy
import helpers.Coordinates

class FixedGoalKeeper(distanceFromGoal: Double = FieldContext.surfaceWidth / 3) : DefenderStrategy(distanceFromGoal) {
    override val side: SideInTeam = SideInTeam.CENTER

    override fun moveWithoutBall(player: Player): Coordinates = moveTowards(player.position, initialPosition)

    override fun shoot(player: Player): Coordinates = shootTowards(getOpponentGoalsCenter(player.gameSide), CLEARANCE)
}