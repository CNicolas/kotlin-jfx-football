package helpers

import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.distanceFromBall.RandomCombinedDistanceFromBallStrategy
import football.player.strategy.combined.distanceFromBall.attack.ForwardStriker
import football.player.strategy.combined.distanceFromBall.defense.OutingGoalKeeper
import football.player.strategy.combined.distanceFromBall.defense.RecoverCrossShootDistanceFromBall
import football.player.strategy.combined.quarters.FollowRecoverCrossShot
import football.player.strategy.combined.quarters.RandomCombinedQuartersStrategy
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
import football.player.strategy.simple.midfield.PassTheBallToClosestAlly
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import java.util.*

const val NUMBER_OF_STRATEGIES = 48
const val NUMBER_OF_DISTINCT_STRATEGIES = 23

fun getRandomSideInTeam(): SideInTeam = SideInTeam.values()[Random().nextInt(SideInTeam.values().size)]

fun createStrategyByNumber(strategyNumber: Int): PlayerStrategy {
    val randomSideInTeam = getRandomSideInTeam()

    return when (strategyNumber) {
        0 -> RandomCombinedQuartersStrategy(UP, Random())
        1 -> RandomCombinedQuartersStrategy(CENTER, Random())
        2 -> RandomCombinedQuartersStrategy(DOWN, Random())
        3 -> RandomCombinedDistanceFromBallStrategy(UP, Random())
        4 -> RandomCombinedDistanceFromBallStrategy(CENTER, Random())
        5 -> RandomCombinedDistanceFromBallStrategy(DOWN, Random())

        6 -> FixedGoalKeeper()
        7 -> DefenderFollowingBall()
        8 -> RunAndShootStraight(UP)
        9 -> RunAndShootStraight(CENTER)
        10 -> RunAndShootStraight(DOWN)
        11 -> PushBallAndShootStraight(UP)
        12 -> PushBallAndShootStraight(CENTER)
        13 -> PushBallAndShootStraight(DOWN)
        14 -> DumbRusherRun(UP)
        15 -> DumbRusherRun(CENTER)
        16 -> DumbRusherRun(DOWN)
        17 -> DumbRusherNormal(UP)
        18 -> DumbRusherNormal(CENTER)
        19 -> DumbRusherNormal(DOWN)
        20 -> DumbRusherShoot(UP)
        21 -> DumbRusherShoot(CENTER)
        22 -> DumbRusherShoot(DOWN)
        23 -> CrossShot(UP)
        24 -> CrossShot(CENTER)
        25 -> CrossShot(DOWN)
        26 -> RunZigZag(UP)
        27 -> RunZigZag(CENTER)
        28 -> RunZigZag(DOWN)
        29 -> StayAtShootDistanceOfTheBall()
        30 -> Overtake(UP)
        31 -> Overtake(DOWN)
        32 -> FollowBallHorizontally()
        33 -> FollowClearBall()
        34 -> RecoverCrossShootDistanceFromBall()
        35 -> FollowRecoverCrossShot(UP)
        36 -> FollowRecoverCrossShot(CENTER)
        37 -> FollowRecoverCrossShot(DOWN)
        38 -> RecoverCrossShootQuarters(UP)
        39 -> RecoverCrossShootQuarters(CENTER)
        40 -> RecoverCrossShootQuarters(DOWN)
        41 -> FollowCrossClearBall()
        42 -> CampInOpponentSurface()
        43 -> ForwardStriker(UP)
        44 -> ForwardStriker(CENTER)
        45 -> ForwardStriker(DOWN)
        46 -> OutingGoalKeeper()
        47 -> PassTheBallToClosestAlly()

        else -> {
            DoesNothing(randomSideInTeam)
        }
    }
}

fun createStrategyByNumberAndRandomSide(strategyNumber: Int): PlayerStrategy {
    val randomSideInTeam = getRandomSideInTeam()

    return createStrategyByNumberAndSide(strategyNumber, randomSideInTeam)
}

fun createStrategyByNumberAndSide(strategyNumber: Int, side: SideInTeam): PlayerStrategy {
    return when (strategyNumber) {
        0 -> RandomCombinedQuartersStrategy(side, Random())
        1 -> RandomCombinedDistanceFromBallStrategy(side, Random())

        2 -> FixedGoalKeeper()
        3 -> DefenderFollowingBall()
        4 -> RunAndShootStraight(side)
        5 -> PushBallAndShootStraight(side)
        6 -> DumbRusherRun(side)
        7 -> DumbRusherNormal(side)
        8 -> DumbRusherShoot(side)
        9 -> CrossShot(side)
        10 -> RunZigZag(side)
        11 -> StayAtShootDistanceOfTheBall()
        12 -> Overtake(side)
        13 -> FollowBallHorizontally()
        14 -> FollowClearBall()
        15 -> RecoverCrossShootDistanceFromBall()
        16 -> FollowRecoverCrossShot(side)
        17 -> RecoverCrossShootQuarters(side)
        18 -> FollowCrossClearBall()
        19 -> CampInOpponentSurface()
        20 -> ForwardStriker(side)
        21 -> OutingGoalKeeper()
        22 -> PassTheBallToClosestAlly()

        else -> {
            DoesNothing(side)
        }
    }
}
