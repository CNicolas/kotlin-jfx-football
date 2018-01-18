package football.player.strategy.simple.attack.dumbRushers

import football.player.ShootingStrength
import football.player.SideInTeam

class DumbRusherNormal(override val side: SideInTeam) : DumbRusher(ShootingStrength.NORMAL)