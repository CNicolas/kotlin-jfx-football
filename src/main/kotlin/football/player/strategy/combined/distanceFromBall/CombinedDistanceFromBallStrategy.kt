package football.player.strategy.combined.distanceFromBall

import football.player.strategy.PlayerStrategy

interface CombinedDistanceFromBallStrategy : PlayerStrategy {
    val defenseAwayFromBallStrategy: PlayerStrategy
    val defenseNextToBallStrategy: PlayerStrategy

    val attackAwayFromBallStrategy: PlayerStrategy
    val attackNextToBallStrategy: PlayerStrategy
}