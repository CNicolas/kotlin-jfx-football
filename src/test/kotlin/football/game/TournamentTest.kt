package football.game

import football.player.SideInTeam
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import helpers.TeamHelpers
import javafx.scene.paint.Color
import main.tournament.Tournament
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class TournamentTest {
    @Test
    fun play_with_2_teams_only_one_should_win() {
        val loser = Team(Color.BLUE, listOf(DoesNothing(SideInTeam.UP)))
        val winner = Team(Color.RED, listOf(DumbRusherRun(SideInTeam.CENTER)))

        val tournament = Tournament()
        val leaderBoard = tournament.playTournament(listOf(loser, winner))

        println("Games played : ${leaderBoard.gamesPlayed}")
        leaderBoard.leaderBoard.map { leaderBoardElement -> "${leaderBoardElement.team.strategies} : ${leaderBoardElement.score}" }
                .forEach { println(it) }

        assertThat(leaderBoard.getWinner().score).isEqualTo(6)
        assertThat(leaderBoard.getWinner().team.strategies).isEqualTo(winner.strategies)
    }

    @Test(enabled = false)
    fun should_play_tournament_with_all_teams_of_4_players() {
        val tournament = Tournament()
        val teams = TeamHelpers.createTeamsOfFourPlayersOnRandomSide()

        println(teams.size)

        val leaderBoard = tournament.playTournament(teams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_with_50_teams_of_4_players() {
        val tournament = Tournament()
        val teams = TeamHelpers.createTeamsOfFourPlayersOnRandomSide()
        Collections.shuffle(teams)
        val tournamentTeams = teams.subList(0, 50)

        val leaderBoard = tournament.playTournament(tournamentTeams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_with_100_teams_of_4_players() {
        val tournament = Tournament()
        val teams = TeamHelpers.createTeamsOfFourPlayersOnRandomSide()
        Collections.shuffle(teams)
        val tournamentTeams = teams.subList(0, 100)

        val leaderBoard = tournament.playTournament(tournamentTeams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_with_100_teams_of_2_players() {
        val tournament = Tournament()
        val teams = TeamHelpers.createTeamsOfTwoPlayers()
        Collections.shuffle(teams)
        val tournamentTeams = teams.subList(0, 100)

        val leaderBoard = tournament.playTournament(tournamentTeams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test(enabled = true)
    fun should_play_tournament_with_300_teams_of_4_players() {
        val tournament = Tournament()
        val teams = TeamHelpers.createTeamsOfFourPlayersOnRandomSide()
        Collections.shuffle(teams)
        val tournamentTeams = teams.subList(0, 300)

        val leaderBoard = tournament.playTournament(tournamentTeams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }
}