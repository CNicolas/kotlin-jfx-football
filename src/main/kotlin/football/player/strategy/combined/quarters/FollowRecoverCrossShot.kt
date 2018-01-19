package football.player.strategy.combined.quarters

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall

class FollowRecoverCrossShot(override val side: SideInTeam) : AbstractCombinedQuartersStrategy() {
    override val defenseStrategy: PlayerStrategy = DefenderFollowingBall()
    override val midDefenseStrategy: PlayerStrategy = StayAtShootDistanceOfTheBall()
    override val midAttackStrategy: PlayerStrategy = RunZigZag(side)
    override val attackStrategy: PlayerStrategy = CrossShot(side)
}