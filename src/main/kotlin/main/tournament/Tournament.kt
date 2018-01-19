package main.tournament

import football.game.FinalScoreStatus.AWAY_WON
import football.game.FinalScoreStatus.HOME_WON
import football.game.GameSide
import football.game.Team
import main.GameRunner

class Tournament {

    fun playTournament(teams: List<Team>): LeaderBoard {
        val leaderBoard = LeaderBoard(teams)

        for (homeIndex in 0 until teams.size) {
            for (awayIndex in 0 until teams.size) {
                if (homeIndex != awayIndex) {
                    teams[homeIndex].gameSide = GameSide.HOME
                    val home = teams[homeIndex].clone()
                    home.resetPositions()

                    teams[awayIndex].gameSide = GameSide.AWAY
                    val away = teams[awayIndex].clone()
                    away.resetPositions()

                    val runner = GameRunner(home, away)
                    val score = runner.play()

                    when (score.status) {
                        HOME_WON -> {
                            leaderBoard.win(homeIndex)
                            leaderBoard.lose(awayIndex)
                        }
                        AWAY_WON -> {
                            leaderBoard.win(awayIndex)
                            leaderBoard.lose(homeIndex)
                        }
                        else -> {
                            leaderBoard.draw(homeIndex, awayIndex)
                        }
                    }
                    leaderBoard.addGoals(homeIndex, score.homeGoals, awayIndex, score.awayGoals)

                    leaderBoard.oneMoreGamePlayed()
                }
            }

            println("$homeIndex on ${teams.size}")
        }

        leaderBoard.orderDescendingByScore()

        return leaderBoard
    }
}