package football.player.strategy.combined.distanceFromBall.defense

import football.FieldContext
import football.player.SideInTeam
import football.player.strategy.combined.distanceFromBall.AbstractCombinedDistanceFromBallStrategy
import football.player.strategy.simple.defense.defender.DefenderFollowingBall
import football.player.strategy.simple.defense.goal.FixedGoalKeeper

class OutingGoalKeeper(distanceFromGoal: Double = FieldContext.surfaceWidth / 2) : AbstractCombinedDistanceFromBallStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override val defenseAwayFromBallStrategy = FixedGoalKeeper(distanceFromGoal)
    override val defenseNextToBallStrategy = DefenderFollowingBall(distanceFromGoal)
    override val attackAwayFromBallStrategy = FixedGoalKeeper(distanceFromGoal)
    override val attackNextToBallStrategy = FixedGoalKeeper(distanceFromGoal)
}