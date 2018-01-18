package football.player.strategy.simple.attack.dumbRushers

import football.player.ShootingStrength
import football.player.SideInTeam

class DumbRusherRun(override val side: SideInTeam) : DumbRusher(ShootingStrength.RUN)