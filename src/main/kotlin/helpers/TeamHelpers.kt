package helpers

import football.game.GameSide
import football.game.Team
import football.player.SideInTeam.DOWN
import football.player.SideInTeam.UP
import football.player.strategy.combined.distanceFromBall.RandomCombinedDistanceFromBallStrategy
import football.player.strategy.combined.quarters.RandomCombinedQuartersStrategy
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import football.player.strategy.simple.defense.goal.FixedGoalKeeper
import javafx.scene.paint.Color
import java.util.*

//fun createRandomTeam(numberOfPlayers: Int): List<PlayerStrategy> {
//    val res = mutableListOf<PlayerStrategy>()
//
//    for (i in 0 until numberOfPlayers) {
//        res.add(createRandomStrategy())
//    }
//
//    return res
//}

class TeamHelpers private constructor() {
    companion object {
        fun printTeams(teams: List<Team>) = teams.forEach { println(it.strategies) }

        fun createTeamsOfOnePlayer(): List<Team> =
                (0 until NUMBER_OF_STRATEGIES).map {
                    Team(Color.BLACK, listOf(createStrategyByNumber(it)))
                }

        fun createTeamsOfOnePlayerOnRandomSide(): List<Team> =
                (0 until NUMBER_OF_DISTINCT_STRATEGIES).map {
                    Team(Color.BLACK, listOf(createStrategyByNumberAndRandomSide(it)))
                }

        fun createTeamsOfTwoPlayers(): List<Team> =
                (0 until NUMBER_OF_STRATEGIES).flatMap { strategy1 ->
                    (strategy1 until NUMBER_OF_STRATEGIES).map { strategy2 ->
                        Team(Color.BLACK, listOf(createStrategyByNumber(strategy1),
                                createStrategyByNumber(strategy2)))
                    }
                }

        fun createTeamsOfTwoPlayersOnRandomSide(): List<Team> =
                (0 until NUMBER_OF_DISTINCT_STRATEGIES).flatMap { strategy1 ->
                    (strategy1 until NUMBER_OF_DISTINCT_STRATEGIES).map { strategy2 ->
                        Team(Color.BLACK, listOf(createStrategyByNumberAndRandomSide(strategy1),
                                createStrategyByNumberAndRandomSide(strategy2)))
                    }
                }

        fun createTeamsOfThreePlayers(): List<Team> =
                (0 until NUMBER_OF_STRATEGIES).flatMap { strategy1 ->
                    (strategy1 until NUMBER_OF_STRATEGIES).flatMap { strategy2 ->
                        (strategy2 until NUMBER_OF_STRATEGIES).map { strategy3 ->
                            Team(Color.BLACK, listOf(createStrategyByNumber(strategy1),
                                    createStrategyByNumber(strategy2),
                                    createStrategyByNumber(strategy3)))
                        }
                    }
                }

        fun createTeamsOfThreePlayersOnRandomSide(): List<Team> =
                (0 until NUMBER_OF_DISTINCT_STRATEGIES).flatMap { strategy1 ->
                    (strategy1 until NUMBER_OF_DISTINCT_STRATEGIES).flatMap { strategy2 ->
                        (strategy2 until NUMBER_OF_DISTINCT_STRATEGIES).map { strategy3 ->
                            Team(Color.BLACK, listOf(createStrategyByNumberAndRandomSide(strategy1),
                                    createStrategyByNumberAndRandomSide(strategy2),
                                    createStrategyByNumberAndRandomSide(strategy3)))
                        }
                    }
                }

        fun createTeamsOfFourPlayers(): List<Team> =
                (0 until NUMBER_OF_STRATEGIES).flatMap { strategy1 ->
                    (strategy1 until NUMBER_OF_STRATEGIES).flatMap { strategy2 ->
                        (strategy2 until NUMBER_OF_STRATEGIES).flatMap { strategy3 ->
                            (strategy3 until NUMBER_OF_STRATEGIES).map { strategy4 ->
                                Team(Color.BLACK, listOf(createStrategyByNumber(strategy1),
                                        createStrategyByNumber(strategy2),
                                        createStrategyByNumber(strategy3),
                                        createStrategyByNumber(strategy4)))
                            }
                        }
                    }
                }

        fun createTeamsOfFourPlayersOnRandomSide(): List<Team> =
                (0 until NUMBER_OF_DISTINCT_STRATEGIES).flatMap { strategy1 ->
                    (strategy1 until NUMBER_OF_DISTINCT_STRATEGIES).flatMap { strategy2 ->
                        (strategy2 until NUMBER_OF_DISTINCT_STRATEGIES).flatMap { strategy3 ->
                            (strategy3 until NUMBER_OF_DISTINCT_STRATEGIES).map { strategy4 ->
                                Team(Color.BLACK, listOf(createStrategyByNumberAndRandomSide(strategy1),
                                        createStrategyByNumberAndRandomSide(strategy2),
                                        createStrategyByNumberAndRandomSide(strategy3),
                                        createStrategyByNumberAndRandomSide(strategy4)))
                            }
                        }
                    }
                }

        fun create100TeamsOf4RandomQuartersPlayers(): List<Team> =
                (0 until 100).map {
                    Team(Color.BLACK, listOf(RandomCombinedQuartersStrategy(getRandomSideInTeam(), Random()),
                            RandomCombinedQuartersStrategy(getRandomSideInTeam(), Random()),
                            RandomCombinedQuartersStrategy(getRandomSideInTeam(), Random()),
                            RandomCombinedQuartersStrategy(getRandomSideInTeam(), Random())))
                }

        fun create100TeamsOf4RandomDistanceFromBallPlayers(): List<Team> =
                (0 until 100).map {
                    Team(Color.BLACK, listOf(RandomCombinedDistanceFromBallStrategy(getRandomSideInTeam(), Random()),
                            RandomCombinedDistanceFromBallStrategy(getRandomSideInTeam(), Random()),
                            RandomCombinedDistanceFromBallStrategy(getRandomSideInTeam(), Random()),
                            RandomCombinedDistanceFromBallStrategy(getRandomSideInTeam(), Random())))
                }

        // region Pre-made Teams

        fun createDoesNothingUPDoesNothingDOWN(color: Color, side: GameSide): Team {
            val team = Team(color, listOf(DoesNothing(UP), DoesNothing(DOWN)))
            team.gameSide = side

            return team
        }

        fun createFixedGoalKeeperDumbRusherRunWithBallUP(color: Color, side: GameSide): Team {
            val team = Team(color, listOf(FixedGoalKeeper(), DumbRusherRun(UP)))
            team.gameSide = side

            return team
        }

        fun createDumbRusherRunUPDumbRusherRunDOWN(color: Color, side: GameSide): Team {
            val team = Team(color, listOf(DumbRusherRun(UP), DumbRusherRun(DOWN)))
            team.gameSide = side

            return team
        }
        // endregion
    }
}