package football.player.strategy.combined.distanceFromBall

import football.FieldContext
import football.player.SideInTeam
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.defense.FollowCrossClearBall

class RecoverAndShoot(distanceFromGoal: Double = FieldContext.fieldTotalWidth / 5) : AbstractCombinedDistanceFromBallStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override val defenseAwayFromBallStrategy = FollowCrossClearBall(distanceFromGoal)
    override val defenseNextToBallStrategy = FollowCrossClearBall(distanceFromGoal)
    override val attackAwayFromBallStrategy = DefenderFollowingBall(distanceFromGoal)
    override val attackNextToBallStrategy = DefenderFollowingBall(distanceFromGoal)
}