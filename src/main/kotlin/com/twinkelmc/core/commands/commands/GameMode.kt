package com.twinkelmc.core.commands.commands

import com.twinkelmc.core.Main
import com.twinkelmc.core.chat.Chat
import org.bukkit.GameMode
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class GameMode {

    fun commandGameMode(sender: CommandSender, args: ArrayList<String>) {

        if (args.size == 2) {
            val receiver = Main.instance.playerFinder.findPlayer(args[1])
            if (receiver == null) {
                Chat.send(sender, "player_not_found", args[1])
                return
            }
            setGameMode(executor = sender, receiver = receiver, args[0])
        } else if (sender is Player && args.size != 0) {
            setGameMode(executor = sender, receiver = sender, args[0])
        } else {
            Chat.send(sender, "please_use", "/gm [gamemode] [player]")
        }

    }

    private fun setGameMode(executor: CommandSender, receiver: Player, gamemode: String) {

        val gameMode = findGameMode(gamemode)
        val msgMode = "$gameMode".lowercase()
        if (gameMode == null) {
            Chat.send(executor, "gamemode_not_found", gamemode)
            return
        }

        receiver.gameMode = gameMode
        if (executor !is Player || executor != receiver) {
            Chat.send(executor, "gamemode_change_other", receiver, msgMode)
        }
        Chat.send(receiver, "gamemode_change_self", msgMode)

    }


    private fun findGameMode(gamemode: String): GameMode? {

        when (gamemode) {
            "0" -> return GameMode.SURVIVAL
            "1" -> return GameMode.CREATIVE
            "2" -> return GameMode.ADVENTURE
            "3" -> return GameMode.SPECTATOR
        }

        if (gamemode.startsWith("sp"))
            return GameMode.SPECTATOR
        if (gamemode.startsWith("c"))
            return GameMode.CREATIVE
        if (gamemode.startsWith("s"))
            return GameMode.SURVIVAL
        if (gamemode.startsWith("a"))
            return GameMode.ADVENTURE

        return null

    }

}