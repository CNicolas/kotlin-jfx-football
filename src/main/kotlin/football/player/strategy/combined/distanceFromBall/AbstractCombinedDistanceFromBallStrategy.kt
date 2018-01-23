package football.player.strategy.combined.distanceFromBall

import football.Ball
import football.game.GameSide
import football.player.Player
import football.player.strategy.AbstractPlayerStrategy
import football.player.strategy.PlayerStrategy
import helpers.Coordinates

abstract class AbstractCombinedDistanceFromBallStrategy : AbstractPlayerStrategy(), CombinedDistanceFromBallStrategy {
    override fun setInitialX(gameSide: GameSide): Double = defenseAwayFromBallStrategy.setInitialX(gameSide)

    override fun setInitialY(): Double = defenseAwayFromBallStrategy.setInitialY()

    override fun moveWithoutBall(player: Player): Coordinates = chooseStrategy(player).moveWithoutBall(player)
    override fun shoot(player: Player): Coordinates = chooseStrategy(player).shoot(player)

    private fun chooseStrategy(player: Player): PlayerStrategy =
            when {
                isInHalfField(player.gameSide, Ball.instance.position) -> when {
                    isAtShootingDistanceOfPlayer(player, Ball.instance.position) -> defenseNextToBallStrategy
                    else -> defenseAwayFromBallStrategy
                }
                else -> when {
                    isAtShootingDistanceOfPlayer(player, Ball.instance.position) -> attackNextToBallStrategy
                    else -> attackAwayFromBallStrategy
                }
            }
}
