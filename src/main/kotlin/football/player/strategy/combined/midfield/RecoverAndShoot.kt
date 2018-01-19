package football.player.strategy.combined.midfield

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.DefenderStrategy
import football.player.strategy.combined.CombinedRunShootStrategy
import football.player.strategy.combined.attack.ZigZagAndCrossShot
import football.player.strategy.simple.defense.FollowClearBall
import helpers.Coordinates
import helpers.distance

class RecoverAndShoot(distanceFromGoal: Double = FieldContext.fieldTotalWidth / 5) : DefenderStrategy(distanceFromGoal), CombinedRunShootStrategy {
    override val side: SideInTeam = SideInTeam.CENTER

    override val runningStrategy: FollowClearBall = FollowClearBall()
    override val shootingStrategy: ZigZagAndCrossShot = ZigZagAndCrossShot(side)

    override fun moveWithoutBall(player: Player): Coordinates {
        return when {
            isBallAtShootingDistance(player) -> shootingStrategy.moveWithoutBall(player)
            else -> runningStrategy.moveWithoutBall(player)
        }
    }

    override fun shoot(player: Player): Coordinates {
        return when {
            isBallInHalfField(player) -> runningStrategy.shoot(player)
            else -> shootingStrategy.shoot(player)
        }
    }

    private fun isBallAtShootingDistance(player: Player): Boolean =
            ShootingStrength.SHOOT.distance > distance(Ball.instance.position, player.position)
}