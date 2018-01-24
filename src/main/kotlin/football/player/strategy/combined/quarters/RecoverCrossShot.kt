package football.player.strategy.combined.quarters

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.distanceFromBall.RecoverAndShoot
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.defense.FollowCrossClearBall

class RecoverCrossShot(override val side: SideInTeam) : AbstractCombinedQuartersStrategy() {
    override val defenseStrategy: PlayerStrategy = RecoverAndShoot()
    override val midDefenseStrategy: PlayerStrategy = FollowCrossClearBall()
    override val midAttackStrategy: PlayerStrategy = DumbRusherShoot(side)
    override val attackStrategy: PlayerStrategy = CrossShot(side)
}