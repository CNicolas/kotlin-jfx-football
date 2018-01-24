package football.player.strategy.combined.distanceFromBall.midfield

import football.player.SideInTeam
import football.player.strategy.combined.distanceFromBall.AbstractCombinedDistanceFromBallStrategy
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.defense.defender.FollowClearBall
import football.player.strategy.simple.midfield.PassToClosestAlly
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall

class RecoverAndPass : AbstractCombinedDistanceFromBallStrategy() {
    override val side: SideInTeam = SideInTeam.CENTER

    override val defenseAwayFromBallStrategy = FollowClearBall()
    override val defenseNextToBallStrategy = PassToClosestAlly()
    override val attackAwayFromBallStrategy = StayAtShootDistanceOfTheBall()
    override val attackNextToBallStrategy = CrossShot(side)
}