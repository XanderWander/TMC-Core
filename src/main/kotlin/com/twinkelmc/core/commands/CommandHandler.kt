package com.twinkelmc.core.commands

import com.twinkelmc.core.Main
import com.twinkelmc.core.chat.Chat
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHandler: CommandExecutor {

    private val classInstance = Main.instance.classInstance
    private val chat = classInstance.chat
    private val gameMode = classInstance.gameMode
    private val give = classInstance.give
    private val mobility = classInstance.mobility
    private val teleport = classInstance.teleport
    private val utility = classInstance.utility

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val list: ArrayList<String> = arrayListOf()
        for (arg in args) {
            list.add(arg)
        }

        if (sender is Player) {

            if (!sender.hasPermission("tmc.core")) {
                val msg = Main.instance.messageFormatter.getMSGFormat("NL", "no_permission")
                sender.sendMessage(msg)
                return false
            }
 
            when (label.lowercase()) {
                "dev" -> {
                    Chat.send(sender, "", "test", 0.0, 0, true, 0f, sender.location)
                }
                "rp" -> {
                    if (args.isEmpty()) sender.setResourcePack(Main.instance.rp) else sender.setResourcePack("https://google.com/")
                }
                "fly" -> mobility.commandFly(sender, list)
                "up" -> mobility.commandUp(sender)
                "top" -> mobility.commandTop(sender)
                "speed" -> mobility.commandSpeed(sender, list)
                "tp" -> teleport.commandTP(sender, list)
                "tphere" -> teleport.commandTPHere(sender, list)
                "back" -> teleport.commandBack(sender)
                "craft" -> utility.commandCraft(sender, list)
                "ptime" -> utility.commandPTime(sender, list)
                "pweather" -> utility.commandPWeather(sender, list)
                "invsee" -> utility.commandInvsee(sender, list)
                "give" -> give.commandGive(sender, list)
                "help" -> chat.commandHelp(sender, list)
                "list" -> chat.commandList(sender, list)
                "msg" -> chat.commandMsg(sender, list)
                "rules" -> chat.commandRules(sender, list)
                "heal" -> utility.commandHeal(sender, list)
                "gm" -> gameMode.commandGameMode(sender, list)
                "gamemode" -> gameMode.commandGameMode(sender, list)
                "spawn" -> sender.teleport(Location(Bukkit.getWorld("world")!!, -160.5, 81.0, -463.5))
                "gms" -> {
                    val ls = arrayListOf("0")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
                "gmc" -> {
                    val ls = arrayListOf("1")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
                "gma" -> {
                    val ls = arrayListOf("2")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
                "gmsp" -> {
                    val ls = arrayListOf("3")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
            }
        } else {
            when (label.lowercase()) {
                "fly" -> mobility.commandFly(sender, list)
                "speed" -> mobility.commandSpeed(sender, list)
                "tp" -> teleport.commandTP(sender, list)
                "ptime" -> utility.commandPTime(sender, list)
                "pweather" -> utility.commandPWeather(sender, list)
                "give" -> give.commandGive(sender, list)
                "help" -> chat.commandHelp(sender, list)
                "list" -> chat.commandList(sender, list)
                "msg" -> chat.commandMsg(sender, list)
                "rules" -> chat.commandRules(sender, list)
                "heal" -> utility.commandHeal(sender, list)
                "gm" -> gameMode.commandGameMode(sender, list)
                "gamemode" -> gameMode.commandGameMode(sender, list)
                "gms" -> {
                    val ls = arrayListOf("0")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
                "gmc" -> {
                    val ls = arrayListOf("1")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
                "gma" -> {
                    val ls = arrayListOf("2")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
                "gmsp" -> {
                    val ls = arrayListOf("3")
                    ls.addAll(args)
                    gameMode.commandGameMode(sender, ls)
                }
            }
        }
        return true
    }

}