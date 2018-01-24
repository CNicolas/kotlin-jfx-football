package football.player.strategy.combined.quarters

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import helpers.NUMBER_OF_DISTINCT_STRATEGIES
import helpers.createStrategyByNumberAndSide
import java.util.*

class RandomQuartersStrategy(override val side: SideInTeam, random: Random) : AbstractCombinedQuartersStrategy() {
    override val defenseStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
    override val midDefenseStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
    override val midAttackStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
    override val attackStrategy: PlayerStrategy = createStrategyByNumberAndSide(random.nextInt(NUMBER_OF_DISTINCT_STRATEGIES), side)
}