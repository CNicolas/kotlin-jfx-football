package football.player.strategy

import football.game.GameSide
import football.player.Player
import football.player.SideInTeam
import helpers.Coordinates

interface PlayerStrategy {
    val side: SideInTeam
    var initialPosition: Coordinates

    fun moveWithoutBall(player: Player): Coordinates
    fun shoot(player: Player): Coordinates

    fun setInitialPosition(gameSide: GameSide): Coordinates
    fun setInitialX(gameSide: GameSide): Double
    fun setInitialY(): Double
}