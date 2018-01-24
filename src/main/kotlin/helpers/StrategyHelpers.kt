package helpers

import football.player.SideInTeam
import football.player.SideInTeam.*
import football.player.strategy.PlayerStrategy
import football.player.strategy.combined.distanceFromBall.RandomCombinedDistanceFromBallStrategy
import football.player.strategy.combined.distanceFromBall.attack.ForwardStriker
import football.player.strategy.combined.distanceFromBall.defense.OutingGoalKeeper
import football.player.strategy.combined.distanceFromBall.defense.RecoverCrossShootDistanceFromBall
import football.player.strategy.combined.distanceFromBall.midfield.RecoverAndPass
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
import football.player.strategy.simple.midfield.PassToClosestAlly
import football.player.strategy.simple.midfield.StayAtShootDistanceOfTheBall
import java.util.*

const val NUMBER_OF_STRATEGIES = 43
const val NUMBER_OF_DISTINCT_STRATEGIES = 24

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
        14 -> DumbRusherRun(CENTER)
        15 -> DumbRusherNormal(CENTER)
        16 -> DumbRusherShoot(CENTER)
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
        41 -> PassToClosestAlly()
        42 -> RecoverAndPass()

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
        22 -> PassToClosestAlly()
        23 -> RecoverAndPass()

        else -> {
            DoesNothing(side)
        }
    }
}
