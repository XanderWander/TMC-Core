package com.twinkelmc.core.events

import com.twinkelmc.core.modules.PlayerModule
import com.twinkelmc.core.utils.LPPrefix
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatEvent: Listener {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {

        event.isCancelled = true

        val prefix = LPPrefix.getPrefix(event.player).replace(" ", "")

        for (player in PlayerModule.players) {

            player.sendMessage("$prefix §7${event.player.name} §8► §f${event.message}")

        }

    }

}