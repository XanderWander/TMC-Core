package com.twinkelmc.core.utils

import com.twinkelmc.core.modules.PlayerModule
import org.bukkit.entity.Player

class PlayerFinder {

    fun findPlayer(player: String): Player? {

        for (search in PlayerModule.players) {
            if (search.name.lowercase().startsWith(player.lowercase()))
                return search
        }
        for (search in PlayerModule.players) {
            if (search.name.lowercase().contains(player.lowercase()))
                return search
        }
        return null

    }

}