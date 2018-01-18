package main.tournament

import football.game.Team

class LeaderBoardElement(val team: Team) {
    var score = 0
    var victories = 0
    var draws = 0
    var losses = 0
    var goalsFor = 0
    var goalsAgainst = 0

    var maxPadding: Int = 130

    override fun toString(): String {
        return team.strategies.toString().padEnd(maxPadding) +
                " | " + score.toString().padEnd(3) +
                " | " + victories.toString().padEnd(3) +
                " | " + draws.toString().padEnd(3) +
                " | " + losses.toString().padEnd(3) +
                " | " + goalsFor.toString().padEnd(4) +
                " | " + goalsAgainst.toString().padEnd(4) +
                " | " + (goalsFor - goalsAgainst).toString().padEnd(4)
    }
}