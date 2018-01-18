package football.player.strategy.combined

import football.player.strategy.PlayerStrategy

interface CombinedRunShootStrategy : PlayerStrategy {
    val runningStrategy: PlayerStrategy
    val shootingStrategy: PlayerStrategy
}