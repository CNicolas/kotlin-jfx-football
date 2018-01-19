package football.player.strategy.combined.runShoot

import football.player.Player
import football.player.SideInTeam
import football.player.strategy.AttackStrategy
import football.player.strategy.combined.runShoot.CombinedRunShootStrategy
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import helpers.Coordinates

class ZigZagAndCrossShot(override val side: SideInTeam) : AttackStrategy(), CombinedRunShootStrategy {
    override val runningStrategy = RunZigZag(side)
    override val shootingStrategy = CrossShot(side)

    override fun moveWithoutBall(player: Player): Coordinates {
        return runningStrategy.moveWithoutBall(player)
    }

    override fun shoot(player: Player): Coordinates {
        return when {
            isAtShootingDistanceOfOpponentGoalCenter(player) -> this.shootingStrategy.shoot(player)
            else -> this.runningStrategy.shoot(player)
        }
    }
}