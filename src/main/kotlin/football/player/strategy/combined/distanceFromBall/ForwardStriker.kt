package football.player.strategy.combined.distanceFromBall

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.runShoot.RecoverAndShoot
import football.player.strategy.simple.attack.camper.InFrontOfBall
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.defense.FollowCrossClearBall

class ForwardStriker(override val side: SideInTeam) : AbstractCombinedDistanceFromBallStrategy() {
    override val defenseNextToBallStrategy: PlayerStrategy = FollowCrossClearBall()
    override val defenseAwayFromBallStrategy: PlayerStrategy = RecoverAndShoot()
    override val attackNextToBallStrategy: PlayerStrategy = CrossShot(side)
    override val attackAwayFromBallStrategy: PlayerStrategy = InFrontOfBall()
}