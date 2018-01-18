package main.tournament

import football.game.FinalScoreStatus.AWAY_WON
import football.game.FinalScoreStatus.HOME_WON
import football.game.GameSide
import football.game.Team
import football.player.strategy.PlayerStrategy
import helpers.NUMBER_OF_RANDOM_SIDE_STRATEGIES
import helpers.NUMBER_OF_STRATEGIES
import helpers.TeamHelpers
import helpers.createStrategyByNumberAndRandomSide
import javafx.scene.paint.Color
import main.GameRunner
import java.util.*

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

    fun createTournament(teamsCount: Int = Int.MAX_VALUE): List<Team> {
        val effectiveTeamsCount = Math.min(teamsCount, NUMBER_OF_STRATEGIES * 4)

        val listOfTeams = mutableListOf<Team>()
        while (listOfTeams.size < effectiveTeamsCount) {
            val team = Team(Color.BLACK, createRandomTeam(4))

            if (!listOfTeams.any { areTeamsEqual(it, team) }) {
                listOfTeams.add(team)
            }
        }

        return listOfTeams
    }

    fun createListOfTeams(): List<Team> {
        val listOfTeams = mutableListOf<Team>()

        // For One player
//        listOfTeams.addAll(TeamHelpers.createTeamsOfOnePlayer())

        // For 2 players
//        listOfTeams.addAll(TeamHelpers.createTeamsOfTwoPlayersOnRandomSide())

        // For 3 players
//        listOfTeams.addAll(TeamHelpers.createTeamsOfThreePlayersOnRandomSide())

        // For 4 players
        listOfTeams.addAll(TeamHelpers.createTeamsOfFourPlayersOnRandomSide())

        return listOfTeams
    }

    private fun createRandomTeam(numberOfPlayers: Int): List<PlayerStrategy> {
        val res = mutableListOf<PlayerStrategy>()

        for (i in 0 until numberOfPlayers) {
            res.add(createRandomStrategy())
        }

        return res
    }

    private fun createRandomStrategy(): PlayerStrategy {
        val strategyNumber = Random().nextInt(NUMBER_OF_RANDOM_SIDE_STRATEGIES)

        return createStrategyByNumberAndRandomSide(strategyNumber)
    }

    private fun areTeamsEqual(teamHome: Team, teamAway: Team): Boolean {
        if (teamHome.strategies.size == teamAway.strategies.size) {
            val equalStrategies = (0 until teamHome.strategies.size).count {
                teamHome.strategies[it].toString() == teamAway.strategies[it].toString()
            }

            return equalStrategies == teamHome.strategies.size
        }

        return false
    }
}