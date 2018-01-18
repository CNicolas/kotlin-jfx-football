package helpers

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Percentage
import org.testng.annotations.Test
import kotlin.system.measureNanoTime

class CalculationTest {
    val tries = 1000

    @Test
    fun getMaxCoordinates_should_give_coordinates_close_at_1percent_of_max_distance_STRAIGHT() {
        var actual: Coordinates
        val nanoTimes = LongArray(tries)

        for (i in 0 until tries) {
            val elapsedTime = measureNanoTime {
                val from = Coordinates(0.0, 150.0)
                val aim = Coordinates(200.0, 150.0)
                val maxDistance = 100.0

                actual = getMaxCoordinates(from, aim, maxDistance)

                val distanceBetweenFromAndActual = distance(from, actual)
                assertThat(distanceBetweenFromAndActual).isCloseTo(maxDistance, Percentage.withPercentage(0.5))
            }

            nanoTimes[i] = elapsedTime
        }

        println("${nanoTimes.average() / 1000000} ms")
    }

    @Test
    fun getMaxCoordinates_should_give_coordinates_close_at_1percent_of_max_distance_Oblique() {
        var actual: Coordinates
        val nanoTimes = LongArray(tries)

        for (i in 0 until tries) {
            val elapsedTime = measureNanoTime {
                val from = Coordinates(0.0, 0.0)
                val aim = Coordinates(200.0, 200.0)
                val maxDistance = 100.0

                actual = getMaxCoordinates(from, aim, maxDistance)

                val distanceBetweenFromAndActual = distance(from, actual)
                assertThat(distanceBetweenFromAndActual).isCloseTo(maxDistance, Percentage.withPercentage(0.5))
            }

            nanoTimes[i] = elapsedTime
        }

        println("${nanoTimes.average() / 1000000} ms")
    }
}