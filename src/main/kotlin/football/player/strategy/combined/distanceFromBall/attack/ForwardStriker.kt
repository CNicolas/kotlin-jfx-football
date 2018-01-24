package football.player.strategy.combined.distanceFromBall.attack

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.distanceFromBall.AbstractCombinedDistanceFromBallStrategy
import football.player.strategy.combined.distanceFromBall.defense.RecoverCrossShootDistanceFromBall
import football.player.strategy.simple.attack.camper.InFrontOfBall
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.defense.defender.FollowCrossClearBall

class ForwardStriker(override val side: SideInTeam) : AbstractCombinedDistanceFromBallStrategy() {
    override val defenseAwayFromBallStrategy: PlayerStrategy = RecoverCrossShootDistanceFromBall()
    override val defenseNextToBallStrategy: PlayerStrategy = FollowCrossClearBall()
    override val attackAwayFromBallStrategy: PlayerStrategy = InFrontOfBall()
    override val attackNextToBallStrategy: PlayerStrategy = CrossShot(side)
}