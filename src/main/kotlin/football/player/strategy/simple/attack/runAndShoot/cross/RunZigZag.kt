package football.player.strategy.simple.attack.runAndShoot.cross

import football.Ball
import football.FieldContext
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.simple.attack.AttackStrategy
import helpers.Coordinates
import java.util.*

class RunZigZag(override val side: SideInTeam) : AttackStrategy() {
    private var direction = when (side) {
        DOWN -> true
        UP -> false
        CENTER -> Random().nextBoolean()
    }

    override fun moveWithoutBall(player: Player): Coordinates {
        val destination = Ball.instance.position

        return moveTowards(player.position, destination)
    }

    override fun shoot(player: Player): Coordinates {
        val opponentsGoalCenter = getOpponentGoalsCenter(player.gameSide)

        val destinationY = when (isInOpponentSurface(player)) {
            true -> when (direction) {
                true -> opponentsGoalCenter.y + (FieldContext.cageHeight / 3)
                false -> opponentsGoalCenter.y - (FieldContext.cageHeight / 3)
            }
            false -> when (direction) {
                true -> opponentsGoalCenter.y + FieldContext.cageHeight
                false -> opponentsGoalCenter.y - FieldContext.cageHeight
            }
        }
        direction = !direction

        val aim = Coordinates(opponentsGoalCenter.x, destinationY)
        return shootTowards(player.position, aim, ShootingStrength.NORMAL)
    }
}