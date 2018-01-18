package football.player

import football.FieldContext

enum class ShootingStrength(private val strengthMultiplier: Double) {
    RUN(0.5), NORMAL(1.0), SHOOT(2.0), CLEARANCE(5.0);

    val distance = apply(FieldContext.shootingDistance)

    private fun apply(distance: Double) = distance * strengthMultiplier
}