package football.player

import football.game.Team
import football.player.strategy.PlayerStrategy
import helpers.Coordinates
import javafx.scene.shape.Circle

class Player(val team: Team, val strategy: PlayerStrategy) {
    var circle: Circle = Circle(0.0, 0.0, 7.0, team.color)
    var position: Coordinates = Coordinates()

    fun setInitialPosition(initialPosition: Coordinates) {
        position = initialPosition

        circle.translateX = position.x
        circle.translateY = position.y
    }

    fun moveTo(): Coordinates = strategy.moveWithoutBall(this)

    fun shootTo(): Coordinates = strategy.shoot(this)

    fun clone(): Player {
        val player = Player(team, strategy)
        player.position = position.clone()
        player.circle = circle

        return player
    }

    override fun toString(): String {
        return "Player$position"
    }
}