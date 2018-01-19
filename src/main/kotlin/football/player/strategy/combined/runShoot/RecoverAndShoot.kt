package football.player.strategy.combined.runShoot

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import football.player.strategy.simple.defense.FollowClearBall
import helpers.Coordinates

class RecoverAndShoot(distanceFromGoal: Double = FieldContext.fieldTotalWidth / 5) : DefenderStrategy(distanceFromGoal), CombinedRunShootStrategy {
    override val side: SideInTeam = SideInTeam.CENTER

    override val runningStrategy: FollowClearBall = FollowClearBall()
    override val shootingStrategy: ZigZagAndCrossShot = ZigZagAndCrossShot(side)

    override fun moveWithoutBall(player: Player): Coordinates {
        return when {
            isAtShootingDistance(player, Ball.instance.position) -> shootingStrategy.moveWithoutBall(player)
            else -> runningStrategy.moveWithoutBall(player)
        }
    }

    override fun shoot(player: Player): Coordinates {
        return when {
            isBallInHalfField(player) -> runningStrategy.shoot(player)
            else -> shootingStrategy.shoot(player)
        }
    }
}