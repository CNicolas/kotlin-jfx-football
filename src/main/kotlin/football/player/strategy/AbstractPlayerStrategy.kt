package football.player.strategy

import football.FieldContext
import football.FieldContext.Companion.fieldHalfHeight
import football.FieldContext.Companion.fieldTotalWidth
import football.FieldContext.Companion.moveDistanceByTurn
import football.game.GameSide
import football.game.GameSide.AWAY
import football.game.GameSide.HOME
import football.player.Player
import football.player.ShootingStrength
import football.player.SideInTeam
import helpers.Coordinates
import helpers.extractFunctionOfLine
import helpers.getMaxCoordinates

abstract class AbstractPlayerStrategy : PlayerStrategy {
    override var initialPosition: Coordinates = Coordinates()

    protected fun moveTowards(from: Coordinates, aim: Coordinates): Coordinates {
        return getMaxCoordinates(from, aim, moveDistanceByTurn)
    }

    protected fun shootTowards(from: Coordinates, aim: Coordinates, strength: ShootingStrength): Coordinates {
        val linearFunction = extractFunctionOfLine(from, aim)
        val variation = 10

        val trueX = when {
            aim.x < from.x -> aim.x - variation
            aim.x > from.x -> aim.x + variation
            else -> aim.x
        }

        val trueY = linearFunction(trueX)

        return getMaxCoordinates(from, Coordinates(trueX, trueY), strength.distance)
    }

    protected fun getOpponentGoalsCenter(player: Player): Coordinates {
        return when (player.team.gameSide) {
            HOME -> getGoalCenter(AWAY)
            else -> getGoalCenter(HOME)
        }
    }

    protected fun getGoalCenter(gameSide: GameSide): Coordinates {
        return when (gameSide) {
            HOME -> Coordinates(0.0, fieldHalfHeight)
            else -> Coordinates(fieldTotalWidth, fieldHalfHeight)
        }
    }

    override fun setInitialPosition(gameSide: GameSide): Coordinates {
        val x = setInitialX(gameSide)
        val y = setInitialY()

        initialPosition = Coordinates(x, y)

        return initialPosition
    }

    protected fun isInTeamHalfField(teamSide: GameSide, coordinates: Coordinates): Boolean {
        return when (teamSide) {
            HOME -> coordinates.x <= FieldContext.fieldHalfWidth
            AWAY -> coordinates.x >= FieldContext.fieldHalfWidth
        }
    }

    protected fun getFieldSide(player: Player): SideInTeam {
        return when {
            player.position.y < FieldContext.fieldHalfWidth -> SideInTeam.UP
            player.position.y > FieldContext.fieldHalfHeight -> SideInTeam.DOWN
            else -> SideInTeam.CENTER
        }
    }

    override fun toString(): String = "${javaClass.simpleName} $side"
}