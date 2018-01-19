package football

import helpers.Coordinates
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class Ball private constructor() {
    var circle: Circle = Circle(0.0, 0.0, 5.0, Color.YELLOW)
    var position: Coordinates = Coordinates()

    init {
        position = Coordinates(FieldContext.fieldHalfWidth, FieldContext.fieldHalfHeight)

        circle.translateX = position.x
        circle.translateY = position.y
    }

    private object Holder {
        val INSTANCE = Ball()
    }

    companion object {
        val instance: Ball by lazy { Holder.INSTANCE }
    }

    fun clone(): Ball {
        val ball = Ball()
        ball.position = position.copy()
        ball.circle = circle

        return ball
    }

    override fun toString(): String {
        return "Ball$position"
    }
}