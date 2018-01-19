package football.game

enum class GameSide {
    HOME,
    AWAY;

    fun opposing() =
            when (this) {
                HOME -> AWAY
                AWAY -> HOME
            }
}