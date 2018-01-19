package main.ihm

import football.Ball
import football.FieldContext
import football.game.Team
import football.player.Player
import helpers.Coordinates
import helpers.distance
import javafx.animation.TranslateTransition
import javafx.util.Duration

class TransitionsManager {
    fun play(states: List<State>) {
        val transitions = mutableListOf<TranslateTransition>()

        println("${states.size} states")

        for (i in 0 until states.size - 1) {
            val state = states[i]

            if (state.shouldAnimate) {
                val nextState = states[i + 1]

                transitions.addAll(createTeamTransitions(state.team1, nextState.team1))
                transitions.addAll(createTeamTransitions(state.team2, nextState.team2))

                if (state.ball.position != nextState.ball.position) {
                    transitions.add(moveBall(state.ball, nextState.ball.position))
                }
            }
        }

        if (transitions.size > 1) {
            playChainOfTransitions(transitions)
        }
    }

    private fun playChainOfTransitions(transitions: List<TranslateTransition>) {
        println("${transitions.size} transitions")

        for (i in 0 until transitions.size - 1) {
            transitions[i].setOnFinished {
                transitions[i + 1].play()
            }
        }

        transitions[0].play()
    }

    private fun createTeamTransitions(teamBefore: Team, teamAfter: Team): List<TranslateTransition> {
        val transitions = mutableListOf<TranslateTransition>()

        if (teamBefore.player1.position != teamAfter.player1.position) {
            transitions.add(movePlayer(teamBefore.player1, teamAfter.player1.position))
        }
        if (teamBefore.player2 !== null && teamAfter.player2 !== null) {
            if (teamBefore.player2!!.position != teamAfter.player2!!.position) {
                transitions.add(movePlayer(teamBefore.player2!!, teamAfter.player2!!.position))
            }
        }
        if (teamBefore.player3 !== null && teamAfter.player3 !== null) {
            if (teamBefore.player3!!.position != teamAfter.player3!!.position) {
                transitions.add(movePlayer(teamBefore.player3!!, teamAfter.player3!!.position))
            }
        }
        if (teamBefore.player4 !== null && teamAfter.player4 !== null) {
            if (teamBefore.player4!!.position != teamAfter.player4!!.position) {
                transitions.add(movePlayer(teamBefore.player4!!, teamAfter.player4!!.position))
            }
        }

        return transitions
    }

    private fun movePlayer(player: Player, to: Coordinates): TranslateTransition {
        val distanceToArrival = distance(player.position, to)
        val duration = (distanceToArrival * 1000) / FieldContext.movingSpeed

        val transition = TranslateTransition(Duration(duration), player.circle)
        transition.toX = to.x
        transition.toY = to.y

        return transition
    }

    private fun moveBall(ball: Ball, to: Coordinates): TranslateTransition {
        val distanceToArrival = distance(ball.position, to)
        val duration = (distanceToArrival * 1000) / FieldContext.movingSpeed

        val transition = TranslateTransition(Duration(duration), ball.circle)
        transition.toX = to.x
        transition.toY = to.y

        return transition
    }
}