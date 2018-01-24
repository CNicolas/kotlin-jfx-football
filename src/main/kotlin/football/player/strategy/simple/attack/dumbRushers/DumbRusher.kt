package football.player.strategy.simple.attack.dumbRushers

import football.Ball
import football.player.Player
import football.player.ShootingStrength
import football.player.strategy.simple.attack.AttackStrategy
import helpers.Coordinates

abstract class DumbRusher(private val strength: ShootingStrength) : AttackStrategy() {
    override fun moveWithoutBall(player: Player): Coordinates = moveTowards(player.position, Ball.instance.position)

    override fun shoot(player: Player): Coordinates = shootTowards(getOpponentGoalsCenter(player.gameSide), strength)
}