package football.player.strategy.combined.distanceFromBall

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy

class CustomCombinedDistanceFromBallStrategy(override val side: SideInTeam,
                                             override val defenseNextToBallStrategy: PlayerStrategy,
                                             override val defenseAwayFromBallStrategy: PlayerStrategy,
                                             override val attackNextToBallStrategy: PlayerStrategy,
                                             override val attackAwayFromBallStrategy: PlayerStrategy) : AbstractCombinedDistanceFromBallStrategy()