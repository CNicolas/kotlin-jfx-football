package football.player.strategy.combined

import football.game.GameSide
import football.player.Player
import football.player.SideInTeam
import football.player.strategy.AbstractPlayerStrategy
import football.player.strategy.PlayerStrategy
import helpers.Coordinates

class CustomCombinedRunShootStrategy(override val side: SideInTeam,
                                     override val runningStrategy: PlayerStrategy,
                                     override val shootingStrategy: PlayerStrategy) : AbstractPlayerStrategy(), CombinedRunShootStrategy {
    override fun moveWithoutBall(player: Player): Coordinates = runningStrategy.moveWithoutBall(player)

    override fun shoot(player: Player): Coordinates = shootingStrategy.shoot(player)

    override fun setInitialX(gameSide: GameSide): Double = runningStrategy.setInitialX(gameSide)

    override fun setInitialY(): Double = runningStrategy.setInitialY()

    override fun toString(): String = "${runningStrategy.javaClass.simpleName}/${shootingStrategy.javaClass.simpleName} $side"
}