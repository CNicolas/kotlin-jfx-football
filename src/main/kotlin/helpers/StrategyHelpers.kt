package helpers

import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.distanceFromBall.attack.ForwardStriker
import football.player.strategy.combined.distanceFromBall.defense.OutingGoalKeeper
import football.player.strategy.combined.distanceFromBall.defense.RecoverCrossShootDistanceFromBall
import football.player.strategy.combined.quarters.FollowRecoverCrossShot
import football.player.strategy.combined.quarters.RecoverCrossShootQuarters
import football.player.strategy.simple.DoesNothing
import football.player.strategy.simple.attack.camper.CampInOpponentSurface
import football.player.strategy.simple.attack.camper.FollowBallHorizontally
import football.player.strategy.simple.attack.dumbRushers.DumbRusherNormal
import football.player.strategy.simple.attack.dumbRushers.DumbRusherRun
import football.player.strategy.simple.attack.dumbRushers.DumbRusherShoot
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.Overtake
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import football.player.strategy.simple.attack.runAndShoot.shootStraight.PushBallAndShootStraight
import football.player.strategy.simple.attack.runAndShoot.shootStraight.RunAndShootStraight
import football.player.strategy.simple.defense.defender.DefenderFollowingBall
import football.player.strategy.simple.defense.defender.FollowClearBall
import football.player.strategy.simple.defense.defender.FollowCrossClearBall
import football.player.strategy.simple.defense.goal.FixedGoalKeeper
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import java.util.*

const val NUMBER_OF_STRATEGIES = 41
const val NUMBER_OF_DISTINCT_STRATEGIES = 20

fun getRandomSideInTeam(): SideInTeam = SideInTeam.values()[Random().nextInt(SideInTeam.values().size)]

fun createStrategyByNumber(strategyNumber: Int): PlayerStrategy {
    val randomSideInTeam = getRandomSideInTeam()

    return when (strategyNumber) {
        0 -> FixedGoalKeeper()
        1 -> DefenderFollowingBall()
        2 -> RunAndShootStraight(UP)
        3 -> RunAndShootStraight(CENTER)
        4 -> RunAndShootStraight(DOWN)
        5 -> PushBallAndShootStraight(UP)
        6 -> PushBallAndShootStraight(CENTER)
        7 -> PushBallAndShootStraight(DOWN)
        8 -> DumbRusherRun(UP)
        9 -> DumbRusherRun(CENTER)
        10 -> DumbRusherRun(DOWN)
        11 -> DumbRusherNormal(UP)
        12 -> DumbRusherNormal(CENTER)
        13 -> DumbRusherNormal(DOWN)
        14 -> DumbRusherShoot(UP)
        15 -> DumbRusherShoot(CENTER)
        16 -> DumbRusherShoot(DOWN)
        17 -> CrossShot(UP)
        18 -> CrossShot(CENTER)
        19 -> CrossShot(DOWN)
        20 -> RunZigZag(UP)
        21 -> RunZigZag(CENTER)
        22 -> RunZigZag(DOWN)
        23 -> StayAtShootDistanceOfTheBall()
        24 -> Overtake(UP)
        25 -> Overtake(DOWN)
        26 -> FollowBallHorizontally()
        27 -> FollowClearBall()
        28 -> RecoverCrossShootDistanceFromBall()
        29 -> FollowRecoverCrossShot(UP)
        30 -> FollowRecoverCrossShot(CENTER)
        31 -> FollowRecoverCrossShot(DOWN)
        32 -> RecoverCrossShootQuarters(UP)
        33 -> RecoverCrossShootQuarters(CENTER)
        34 -> RecoverCrossShootQuarters(DOWN)
        35 -> FollowCrossClearBall()
        36 -> CampInOpponentSurface()
        37 -> ForwardStriker(UP)
        38 -> ForwardStriker(CENTER)
        39 -> ForwardStriker(DOWN)
        40 -> OutingGoalKeeper()

        else -> {
            DoesNothing(randomSideInTeam)
        }
    }
}

fun createStrategyByNumberAndRandomSide(strategyNumber: Int): PlayerStrategy {
    val randomSideInTeam = getRandomSideInTeam()

    return when (strategyNumber) {
        0 -> FixedGoalKeeper()
        1 -> DefenderFollowingBall()
        2 -> RunAndShootStraight(randomSideInTeam)
        3 -> PushBallAndShootStraight(randomSideInTeam)
        4 -> DumbRusherRun(randomSideInTeam)
        5 -> DumbRusherNormal(randomSideInTeam)
        6 -> DumbRusherShoot(randomSideInTeam)
        7 -> CrossShot(randomSideInTeam)
        8 -> RunZigZag(randomSideInTeam)
        9 -> StayAtShootDistanceOfTheBall()
        10 -> Overtake(randomSideInTeam)
        11 -> FollowBallHorizontally()
        12 -> FollowClearBall()
        13 -> RecoverCrossShootDistanceFromBall()
        14 -> FollowRecoverCrossShot(randomSideInTeam)
        15 -> RecoverCrossShootQuarters(randomSideInTeam)
        16 -> FollowCrossClearBall()
        17 -> CampInOpponentSurface()
        18 -> ForwardStriker(randomSideInTeam)
        19 -> OutingGoalKeeper()

        else -> {
            DoesNothing(randomSideInTeam)
        }
    }
}

fun createStrategyByNumberAndSide(strategyNumber: Int, side: SideInTeam): PlayerStrategy {
    return when (strategyNumber) {
        0 -> FixedGoalKeeper()
        1 -> DefenderFollowingBall()
        2 -> RunAndShootStraight(side)
        3 -> PushBallAndShootStraight(side)
        4 -> DumbRusherRun(side)
        5 -> DumbRusherNormal(side)
        6 -> DumbRusherShoot(side)
        7 -> CrossShot(side)
        8 -> RunZigZag(side)
        9 -> StayAtShootDistanceOfTheBall()
        10 -> Overtake(side)
        11 -> FollowBallHorizontally()
        12 -> FollowClearBall()
        13 -> RecoverCrossShootDistanceFromBall()
        14 -> FollowRecoverCrossShot(side)
        15 -> RecoverCrossShootQuarters(side)
        16 -> FollowCrossClearBall()
        17 -> CampInOpponentSurface()
        18 -> ForwardStriker(side)
        19 -> OutingGoalKeeper()

        else -> {
            DoesNothing(side)
        }
    }
}
