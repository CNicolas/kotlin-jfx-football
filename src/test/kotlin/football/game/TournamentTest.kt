package football.game

import football.player.SideInTeam
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import javafx.scene.paint.Color
import main.tournament.Tournament
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.util.*

class TournamentTest {
    @Test
    fun should_initialize_tournament_of_5_games() {
        val tournament = Tournament()
        val teams = tournament.createTournament(5)

        teams.map { team: Team -> "${team.strategies}" }
                .forEach { println(it) }

        assertThat(tournament).isNotNull()
    }

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

    @Test
    fun should_play_tournament_of_5_teams() {
        val tournament = Tournament()
        val teams = tournament.createTournament(5)

        val leaderBoard = tournament.playTournament(teams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_of_20_teams() {
        val tournament = Tournament()
        val teams = tournament.createTournament(20)

        val leaderBoard = tournament.playTournament(teams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_with_teams_of_4_players() {
        val tournament = Tournament()
        val teams = tournament.createListOfTeams()

        println(teams.size)

        val leaderBoard = tournament.playTournament(teams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_play_tournament_with_300_teams_of_4_players() {
        val tournament = Tournament()
        val teams = tournament.createListOfTeams()
        Collections.shuffle(teams)
        val tournamentTeams = teams.subList(0, 300)

        val leaderBoard = tournament.playTournament(tournamentTeams)

        println(leaderBoard)
        println()
        println("Games played : ${leaderBoard.gamesPlayed}, with ${leaderBoard.leaderBoard.size} teams")
        println("${leaderBoard.getWinner().team.strategies}")
    }

    @Test
    fun should_create_all_possible_teams_not_randomly() {
        val tournament = Tournament()
        val teams = tournament.createListOfTeams()

        println(teams.size)
    }
}