package football.game

import football.player.SideInTeam.*
import football.player.strategy.combined.distanceFromBall.CustomCombinedDistanceFromBallStrategy
import football.player.strategy.combined.quarters.FollowRecoverCrossShot
import football.player.strategy.combined.runShoot.RecoverAndShoot
import football.player.strategy.combined.runShoot.ZigZagAndCrossShot
import football.player.strategy.simple.attack.camper.CampInOpponentSurface
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.Overtake
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.defense.FollowClearBall
import football.player.strategy.simple.defense.FollowCrossClearBall
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

    @Test
    fun team_home_should_win() {
        val home = Team(Color.BLUE, listOf(
                CustomCombinedDistanceFromBallStrategy(UP, RecoverAndShoot(), RunZigZag(UP), ZigZagAndCrossShot(UP), DefenderFollowingBall()),
                CustomCombinedDistanceFromBallStrategy(DOWN, Overtake(DOWN), CampInOpponentSurface(), ZigZagAndCrossShot(DOWN), FollowRecoverCrossShot(DOWN)),
                CustomCombinedDistanceFromBallStrategy(CENTER, FollowClearBall(), RunZigZag(CENTER), FollowCrossClearBall(), DefenderFollowingBall()),
                CustomCombinedDistanceFromBallStrategy(UP, CrossShot(UP), FollowClearBall(), FollowRecoverCrossShot(UP), FollowClearBall())
        ))
        val away = TeamHelpers.createDumbRusherRunUPDumbRusherRunDOWN(Color.RED, GameSide.AWAY)

        val runner = GameRunner(home, away)
        runner.play()

        assertThat(home.score).isEqualTo(3)
        assertThat(away.score).isEqualTo(0)
    }
}