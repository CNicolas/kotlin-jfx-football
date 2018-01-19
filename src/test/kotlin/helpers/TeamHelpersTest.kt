package helpers

import org.assertj.core.api.Assertions
import org.testng.annotations.Test

class TeamHelpersTest {

    @Test
    fun should_create_all_teams_of_1_players() {
        val teams = TeamHelpers.createTeamsOfOnePlayer()

        println(teams.size)
        Assertions.assertThat(teams.size.toString())
                .isEqualTo(numberOfCombinationsToString(1, NUMBER_OF_STRATEGIES))
    }

    @Test
    fun should_create_teams_of_1_players_on_random_sides() {
        val teams = TeamHelpers.createTeamsOfOnePlayerOnRandomSide()

        println(teams.size)
        Assertions.assertThat(teams.size.toString())
                .isEqualTo(numberOfCombinationsToString(1, NUMBER_OF_DISTINCT_STRATEGIES))
    }

    @Test
    fun should_create_all_teams_of_2_players() {
        val teams = TeamHelpers.createTeamsOfTwoPlayers()

        val numberOfCombinations = numberOfCombinations(2, NUMBER_OF_STRATEGIES)
        println(teams.size)
        Assertions.assertThat(teams.size.toString())
                .isEqualTo((numberOfCombinations + NUMBER_OF_STRATEGIES).toString())
    }

    @Test
    fun should_create_teams_of_2_players_on_random_sides() {
        val teams = TeamHelpers.createTeamsOfTwoPlayersOnRandomSide()

        val numberOfCombinations = numberOfCombinations(2, NUMBER_OF_DISTINCT_STRATEGIES)
        println(teams.size)
        Assertions.assertThat(teams.size.toString())
                .isEqualTo((numberOfCombinations + NUMBER_OF_DISTINCT_STRATEGIES).toString())
    }

    @Test
    fun should_create_all_teams_of_3_players() {
        val teams = TeamHelpers.createTeamsOfThreePlayers()

        val numberOfCombinations = numberOfCombinations(3, NUMBER_OF_STRATEGIES)
        println(teams.size)
        Assertions.assertThat(teams.size.toString())
                .isEqualTo((numberOfCombinations + Math.pow(NUMBER_OF_STRATEGIES.toDouble(), 2.0)).toString())
    }

    @Test
    fun should_create_teams_of_3_players_on_random_sides() {
        val teams = TeamHelpers.createTeamsOfThreePlayersOnRandomSide()

        val numberOfCombinations = numberOfCombinations(3, NUMBER_OF_DISTINCT_STRATEGIES)
        println(teams.size)
        Assertions.assertThat(teams.size.toString())
                .isEqualTo((numberOfCombinations + Math.pow(NUMBER_OF_DISTINCT_STRATEGIES.toDouble(), 2.0)).toString())
    }


    @Test(enabled = false)
    fun should_create_all_teams_of_4_players() {
        val teams = TeamHelpers.createTeamsOfFourPlayers()

        println(teams.size)
    }

    @Test
    fun should_create_teams_of_4_players_on_random_sides() {
        val teams = TeamHelpers.createTeamsOfFourPlayersOnRandomSide()

        println(teams.size)
    }

    @Test
    fun should_create_100_teams_of_4_random_quarters_players() {
        TeamHelpers.printTeams(TeamHelpers.create100TeamsOf4RandomQuartersPlayers())
    }
}
