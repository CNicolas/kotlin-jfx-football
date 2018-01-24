package football.game

import helpers.TeamHelpers
import javafx.scene.paint.Color
import main.GameRunner
import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class GameRunnerTest {
    @Test
    fun should_play_twice_exactly_the_same_game() {
        val team1 = TeamHelpers.createFixedGoalKeeperDumbRusherRunWithBallUP(Color.BLUE, GameSide.HOME)
        val team2 = TeamHelpers.createDoesNothingUPDoesNothingDOWN(Color.RED, GameSide.AWAY)

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(3)
        assertThat(team2.score).isEqualTo(0)

        runner.play()

        assertThat(team1.score).isEqualTo(3)
        assertThat(team2.score).isEqualTo(0)
    }

    @Test
    fun full_DumbRusherRunWithBall_on_each_side() {
        val team1 = TeamHelpers.createDumbRusherRunUPDumbRusherRunDOWN(Color.BLUE, GameSide.HOME)
        val team2 = TeamHelpers.createDumbRusherRunUPDumbRusherRunDOWN(Color.RED, GameSide.AWAY)

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(0)
        assertThat(team2.score).isEqualTo(0)
    }

    @Test
    fun team_away_should_win() {
        val team1 = TeamHelpers.createDoesNothingUPDoesNothingDOWN(Color.BLUE, GameSide.HOME)
        val team2 = TeamHelpers.createDumbRusherRunUPDumbRusherRunDOWN(Color.RED, GameSide.AWAY)

        val runner = GameRunner(team1, team2)
        runner.play()

        assertThat(team1.score).isEqualTo(0)
        assertThat(team2.score).isEqualTo(3)
    }
}