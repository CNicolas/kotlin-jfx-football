package football.player.strategy.combined.quarters

import football.player.strategy.PlayerStrategy

interface CombinedQuartersStrategy : PlayerStrategy {
    val defenseStrategy: PlayerStrategy
    val midDefenseStrategy: PlayerStrategy
    val midAttackStrategy: PlayerStrategy
    val attackStrategy: PlayerStrategy
}