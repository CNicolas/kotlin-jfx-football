package football.game

import football.game.FinalScoreStatus.*

class Score(val status: FinalScoreStatus, val homeGoals: Int, val awayGoals: Int) {
    companion object {
        fun calculate(home: Team, away: Team): Score {
            return when {
                home.score > away.score -> Score(HOME_WON, home.score, away.score)
                home.score < away.score -> Score(AWAY_WON, home.score, away.score)
                else -> Score(DRAW, home.score, away.score)
            }
        }
    }
}