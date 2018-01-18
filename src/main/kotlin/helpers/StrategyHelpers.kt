package helpers

import football.player.SideInTeam
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.attack.RunStraightAndCrossShot
import football.player.strategy.combined.attack.ZigZagAndCrossShot
import football.player.strategy.combined.midfield.RecoverAndShoot
import football.player.strategy.simple.DoesNothing
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
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import java.util.*

const val NUMBER_OF_STRATEGIES = 35
const val NUMBER_OF_RANDOM_SIDE_STRATEGIES = 16

fun createStrategyByNumber(strategyNumber: Int): PlayerStrategy {
    val randomSideInTeam = SideInTeam.values()[Random().nextInt(SideInTeam.values().size)]

    return when (strategyNumber) {
        0 -> FixedGoalKeeper()
        1 -> DefenderFollowingBall()
        2 -> RunAndShootStraight(SideInTeam.UP)
        3 -> RunAndShootStraight(SideInTeam.CENTER)
        4 -> RunAndShootStraight(SideInTeam.DOWN)
        5 -> PushBallAndShootStraight(SideInTeam.UP)
        6 -> PushBallAndShootStraight(SideInTeam.CENTER)
        7 -> PushBallAndShootStraight(SideInTeam.DOWN)
        8 -> DumbRusherRun(SideInTeam.UP)
        9 -> DumbRusherRun(SideInTeam.CENTER)
        10 -> DumbRusherRun(SideInTeam.DOWN)
        11 -> DumbRusherNormal(SideInTeam.UP)
        12 -> DumbRusherNormal(SideInTeam.CENTER)
        13 -> DumbRusherNormal(SideInTeam.DOWN)
        14 -> DumbRusherShoot(SideInTeam.UP)
        15 -> DumbRusherShoot(SideInTeam.CENTER)
        16 -> DumbRusherShoot(SideInTeam.DOWN)
        17 -> CrossShot(SideInTeam.UP)
        18 -> CrossShot(SideInTeam.CENTER)
        19 -> CrossShot(SideInTeam.DOWN)
        20 -> RunZigZag(SideInTeam.UP)
        21 -> RunZigZag(SideInTeam.CENTER)
        22 -> RunZigZag(SideInTeam.DOWN)
        23 -> StayAtShootDistanceOfTheBall()
        24 -> RunStraightAndCrossShot(SideInTeam.UP)
        25 -> RunStraightAndCrossShot(SideInTeam.CENTER)
        26 -> RunStraightAndCrossShot(SideInTeam.DOWN)
        27 -> ZigZagAndCrossShot(SideInTeam.UP)
        28 -> ZigZagAndCrossShot(SideInTeam.CENTER)
        29 -> ZigZagAndCrossShot(SideInTeam.DOWN)
        30 -> Overtake(SideInTeam.UP)
        31 -> Overtake(SideInTeam.DOWN)
        32 -> FollowBallHorizontally()
        33 -> FollowClearBall()
        34 -> RecoverAndShoot()

        else -> {
            DoesNothing(randomSideInTeam)
        }
    }
}

fun createStrategyByNumberAndRandomSide(strategyNumber: Int): PlayerStrategy {
    val randomSideInTeam = SideInTeam.values()[Random().nextInt(SideInTeam.values().size)]

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

        else -> {
            DoesNothing(randomSideInTeam)
        }
    }
}
