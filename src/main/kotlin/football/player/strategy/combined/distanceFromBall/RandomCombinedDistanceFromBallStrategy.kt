package football.player.strategy.combined.distanceFromBall

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import helpers.NUMBER_OF_DISTINCT_STRATEGIES
import helpers.createStrategyByNumberAndSide
import java.util.*

class RandomCombinedDistanceFromBallStrategy(override val side: SideInTeam, random: Random) : AbstractCombinedDistanceFromBallStrategy() {
    override val defenseAwayFromBallStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
    override val defenseNextToBallStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
    override val attackAwayFromBallStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
    override val attackNextToBallStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)

    override fun toString() = "${javaClass.simpleName}($defenseNextToBallStrategy, $defenseAwayFromBallStrategy, $attackNextToBallStrategy, $attackAwayFromBallStrategy)"
}