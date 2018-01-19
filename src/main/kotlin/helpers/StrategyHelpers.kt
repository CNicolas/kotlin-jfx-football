package helpers

import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.quarters.FollowRecoverCrossShot
import football.player.strategy.combined.quarters.RecoverCrossShot
import football.player.strategy.combined.runShoot.RecoverAndShoot
import football.player.strategy.combined.runShoot.RunStraightAndCrossShot
import football.player.strategy.combined.runShoot.ZigZagAndCrossShot
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
import football.player.strategy.simple.defense.DefenderFollowingBall
import football.player.strategy.simple.defense.FixedGoalKeeper
import football.player.strategy.simple.defense.FollowClearBall
import football.player.strategy.simple.defense.FollowCrossClearBall
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import java.util.*

const val NUMBER_OF_STRATEGIES = 43
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
        24 -> RunStraightAndCrossShot(UP)
        25 -> RunStraightAndCrossShot(CENTER)
        26 -> RunStraightAndCrossShot(DOWN)
        27 -> ZigZagAndCrossShot(UP)
        28 -> ZigZagAndCrossShot(CENTER)
        29 -> ZigZagAndCrossShot(DOWN)
        30 -> Overtake(UP)
        31 -> Overtake(DOWN)
        32 -> FollowBallHorizontally()
        33 -> FollowClearBall()
        34 -> RecoverAndShoot()
        35 -> FollowRecoverCrossShot(UP)
        36 -> FollowRecoverCrossShot(CENTER)
        37 -> FollowRecoverCrossShot(DOWN)
        38 -> RecoverCrossShot(UP)
        39 -> RecoverCrossShot(CENTER)
        40 -> RecoverCrossShot(DOWN)
        41 -> FollowCrossClearBall()
        42 -> CampInOpponentSurface()

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
        10 -> RunStraightAndCrossShot(randomSideInTeam)
        11 -> ZigZagAndCrossShot(randomSideInTeam)
        12 -> Overtake(randomSideInTeam)
        13 -> FollowBallHorizontally()
        14 -> FollowClearBall()
        15 -> RecoverAndShoot()
        16 -> FollowRecoverCrossShot(randomSideInTeam)
        17 -> RecoverCrossShot(randomSideInTeam)
        18 -> FollowCrossClearBall()
        19 -> CampInOpponentSurface()

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
        10 -> RunStraightAndCrossShot(side)
        11 -> ZigZagAndCrossShot(side)
        12 -> Overtake(side)
        13 -> FollowBallHorizontally()
        14 -> FollowClearBall()
        15 -> RecoverAndShoot()
        16 -> FollowRecoverCrossShot(side)
        17 -> RecoverCrossShot(side)
        18 -> FollowCrossClearBall()
        19 -> CampInOpponentSurface()

        else -> {
            DoesNothing(side)
        }
    }
}
