package football.player.strategy.combined.quarters

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.distanceFromBall.defense.RecoverCrossShootDistanceFromBall
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.defense.defender.FollowCrossClearBall

class RecoverCrossShootQuarters(override val side: SideInTeam) : AbstractCombinedQuartersStrategy() {
    override val defenseStrategy: PlayerStrategy = RecoverCrossShootDistanceFromBall()
    override val midDefenseStrategy: PlayerStrategy = FollowCrossClearBall()
    override val midAttackStrategy: PlayerStrategy = DumbRusherShoot(side)
    override val attackStrategy: PlayerStrategy = CrossShot(side)
}