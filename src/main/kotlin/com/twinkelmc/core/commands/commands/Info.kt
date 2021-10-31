package com.twinkelmc.core.commands.commands

import com.twinkelmc.core.Main
import com.twinkelmc.core.chat.Chat
import com.twinkelmc.core.modules.PlayerModule
import com.twinkelmc.core.utils.LPPrefix
import org.bukkit.command.CommandSender

class Info {

    fun commandList(sender: CommandSender, args: ArrayList<String>) {
        var msg = Chat.get(sender, "list_title", PlayerModule.players.size)
        sender.sendMessage("")
        if (PlayerModule.players.size == 1)
            msg = msg.replace("spelers", "speler")
        sender.sendMessage(msg)
        sender.sendMessage("§8§m-------------------------------------------")
        sender.sendMessage("")
        var send = ""
        for (player in PlayerModule.players) {
            send = if (send == "") {
                "§7${LPPrefix.getPrefix(player)}${player.name}"
            } else {
                "$send§7, ${LPPrefix.getPrefix(player)}${player.name}"
            }
            if (send.length > 100) {
                sender.sendMessage(send)
                send = ""
            }
        }
        if (send != "")
            sender.sendMessage(send)
        sender.sendMessage("")
        sender.sendMessage("§8§m-------------------------------------------")

    }

    fun commandMsg(sender: CommandSender, args: ArrayList<String>) {
        TODO()
    }

    fun commandRules(sender: CommandSender, args: ArrayList<String>) {
        TODO()
    }

    fun commandHelp(sender: CommandSender, args: ArrayList<String>) {
        TODO()
    }

}