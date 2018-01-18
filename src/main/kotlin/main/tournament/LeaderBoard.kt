package main.tournament

import football.game.Team

class LeaderBoard(teamsList: List<Team>) {
    var gamesPlayed: Int = 0
        private set
    var leaderBoard: List<LeaderBoardElement>
            = teamsList.map { team -> LeaderBoardElement(team) }
        private set

    fun win(index: Int) {
        leaderBoard[index].score += 3
        leaderBoard[index].victories++
    }

    fun draw(index1: Int, index2: Int) {
        leaderBoard[index1].score += 1
        leaderBoard[index1].draws++

        leaderBoard[index2].score += 1
        leaderBoard[index2].draws++
    }

    fun lose(index: Int) {
        leaderBoard[index].losses++
    }

    fun addGoals(homeIndex: Int, homeGoals: Int, awayIndex: Int, awayGoals: Int) {
        leaderBoard[homeIndex].goalsFor += homeGoals
        leaderBoard[homeIndex].goalsAgainst += awayGoals

        leaderBoard[awayIndex].goalsFor += awayGoals
        leaderBoard[awayIndex].goalsAgainst += homeGoals
    }

    fun getWinner(): LeaderBoardElement =
            leaderBoard.sortedByDescending { leaderBoardElement -> leaderBoardElement.score }
                    .first()

    fun oneMoreGamePlayed() {
        gamesPlayed++
    }

    fun orderDescendingByScore() {
        leaderBoard = leaderBoard.sortedByDescending { it.score }
    }

    override fun toString(): String {
        val maxPadding = leaderBoard.map { it.team.strategies.toString().length }.maxBy { it }
        leaderBoard.forEach { it.maxPadding = maxPadding!! }

        var outputString = leaderBoard
                .mapIndexed { index, elem -> "${(index + 1).toString().padEnd(3)} | $elem" }
                .joinToString("\n")

        outputString = "Pos | ${"Team".padEnd(maxPadding!!)} | Pts | W   | D   | L   | GF   | GA   | Diff \n" + outputString

        return outputString
    }
}