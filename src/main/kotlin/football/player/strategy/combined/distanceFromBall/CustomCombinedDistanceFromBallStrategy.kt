package football.player.strategy.combined.distanceFromBall

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy

class CustomCombinedDistanceFromBallStrategy(override val side: SideInTeam,
                                             override val defenseAwayFromBallStrategy: PlayerStrategy,
                                             override val defenseNextToBallStrategy: PlayerStrategy,
                                             override val attackAwayFromBallStrategy: PlayerStrategy,
                                             override val attackNextToBallStrategy: PlayerStrategy) : AbstractCombinedDistanceFromBallStrategy() {

    override fun toString(): String = "${javaClass.simpleName}($defenseAwayFromBallStrategy, $defenseNextToBallStrategy, $attackAwayFromBallStrategy, $attackNextToBallStrategy)"
}