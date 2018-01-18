package football.game

import football.player.Player
import football.player.strategy.PlayerStrategy
import javafx.scene.paint.Color

class Team(val color: Color, val strategies: List<PlayerStrategy>) {
    var gameSide: GameSide = GameSide.HOME
        set(value) {
            field = value
            strategies.map { it.setInitialPosition(field) }
        }

    var player1: Player = Player(this, strategies[0])
    var player2: Player? = null
    var player3: Player? = null
    var player4: Player? = null

    var score = 0

    init {
        if (strategies.size > 1) {
            player2 = Player(this, strategies[1])
            if (strategies.size > 2) {
                player3 = Player(this, strategies[2])
                if (strategies.size > 3) {
                    player4 = Player(this, strategies[3])
                }
            }
        }

        resetPositions()
    }

    fun resetPositions() {
        player1.setInitialPosition(player1.strategy.initialPosition)
        player2?.setInitialPosition(player2?.strategy!!.initialPosition)
        player3?.setInitialPosition(player3?.strategy!!.initialPosition)
        player4?.setInitialPosition(player4?.strategy!!.initialPosition)
    }

    fun clone(): Team {
        val team = Team(color, strategies)
        team.player1 = player1.clone()
        team.player2 = player2?.clone()
        team.player3 = player3?.clone()
        team.player4 = player4?.clone()
        team.gameSide = gameSide
        team.score = score

        return team
    }

    override fun toString(): String {
        return "Team($gameSide, $score, [$player1, $player2, $player3, $player4])"
    }
}