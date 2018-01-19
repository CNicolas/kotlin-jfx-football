package football.player.strategy.combined.runShoot

import football.player.strategy.PlayerStrategy

interface CombinedRunShootStrategy : PlayerStrategy {
    val runningStrategy: PlayerStrategy
    val shootingStrategy: PlayerStrategy
}