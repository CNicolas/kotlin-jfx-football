package football.game

import football.game.GameSide.AWAY
import football.game.GameSide.HOME

class GameContext private constructor() {
    companion object {
        var teamHome: Team? = null
        var teamAway: Team? = null

        fun getOpposingTeam(gameSide: GameSide): Team {
            return when (gameSide) {
                HOME -> teamAway!!
                AWAY -> teamHome!!
            }
        }
    }
}