package football.player.strategy.combined.quarters

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy

class CustomCombinedQuartersStrategy(override val side: SideInTeam,
                                     override val defenseStrategy: PlayerStrategy,
                                     override val midDefenseStrategy: PlayerStrategy,
                                     override val midAttackStrategy: PlayerStrategy,
                                     override val attackStrategy: PlayerStrategy) : AbstractCombinedQuartersStrategy() {

    override fun toString() = "${javaClass.simpleName}($defenseStrategy, $midDefenseStrategy, $midAttackStrategy, $attackStrategy)"
}
