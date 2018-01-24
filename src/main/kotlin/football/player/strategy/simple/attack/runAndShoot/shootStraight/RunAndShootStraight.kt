package football.player.strategy.simple.attack.runAndShoot.shootStraight

import football.Ball
import football.FieldContext
import football.game.GameSide
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.strategy.simple.attack.AttackStrategy
import helpers.Coordinates

class RunAndShootStraight(override val side: SideInTeam) : AttackStrategy() {
    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val destination = getOpponentGoalsCenter(player.gameSide)
        val strength = when (isInOpponentSurface(player)) {
            true -> ShootingStrength.SHOOT
            false -> ShootingStrength.RUN
        }

        return shootTowards(player.position, destination, strength)
    }

    override fun setInitialX(gameSide: GameSide): Double {
        return when (gameSide) {
            GameSide.HOME -> FieldContext.fieldTotalWidth / 3
            else -> (2 * FieldContext.fieldTotalWidth) / 3
        }
    }

    override fun setInitialY(): Double {
        return when (side) {
            SideInTeam.UP -> FieldContext.fieldTotalHeight / 3
            SideInTeam.DOWN -> (2 * FieldContext.fieldTotalHeight) / 3
            else -> FieldContext.fieldHalfWidth
        }
    }
}