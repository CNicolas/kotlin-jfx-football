package football.player.strategy.combined.distanceFromBall

import football.player.strategy.PlayerStrategy

interface CombinedDistanceFromBallStrategy : PlayerStrategy {
    val defenseNextToBallStrategy: PlayerStrategy
    val defenseAwayFromBallStrategy: PlayerStrategy

    val attackNextToBallStrategy: PlayerStrategy
    val attackAwayFromBallStrategy: PlayerStrategy
}