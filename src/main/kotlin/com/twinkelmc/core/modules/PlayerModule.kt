package com.twinkelmc.core.modules

import org.bukkit.entity.Player

class PlayerModule {

    companion object {
        val players = arrayListOf<Player>()

        fun register(player: Player) {
            players.add(player)
        }

        fun register(newPlayers: Collection<Player>) {
            players.addAll(newPlayers)
        }

        fun remove(player: Player) {
            players.remove(player)
        }

        fun contains(player: Player): Boolean {
            return players.contains(player)
        }
    }

}