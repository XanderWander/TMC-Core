package com.twinkelmc.core.commands.commands

import com.twinkelmc.core.Main
import com.twinkelmc.core.chat.Chat
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.NumberFormatException

class Teleport {

    private val lastLocationMap = hashMapOf<Player, Location>()

    fun commandTPHere(player: Player, args: ArrayList<String>) {
        when(val target = Main.instance.playerFinder.findPlayer(args[0])) {
            null -> Chat.send(player, "player_not_found", args[0])
            player -> Chat.send(player, "teleport_to_self")
            else -> {
                lastLocationMap[target] = target.location
                target.teleport(player)
                Chat.send(player, "teleport_here_other", target)
                Chat.send(target, "teleport_here_self", player)
            }
        }
    }

    fun commandBack(player: Player) {
        if (lastLocationMap.containsKey(player)) {
            player.teleport(lastLocationMap[player]!!)
            lastLocationMap[player] = player.location
            Chat.send(player, "teleport_back")
        } else {
            Chat.send(player, "teleport_back")
        }
    }

    fun commandTP(sender: CommandSender, args: ArrayList<String>) {
        if (sender is Player) {
            when(args.size) {
                1 -> {
                    val target = Main.instance.playerFinder.findPlayer(args[0])
                    if (target == null) {
                        Chat.send(sender, "player_not_found", args[0])
                    } else {
                        teleport(sender, sender, target)
                    }
                }
                2 -> {
                    val recipient = Main.instance.playerFinder.findPlayer(args[0])
                    if (recipient == null) {
                        Chat.send(sender, "player_not_found", args[0])
                        return
                    }
                    val target = Main.instance.playerFinder.findPlayer(args[1])
                    if (target == null) {
                        Chat.send(sender, "player_not_found", args[1])
                        return
                    }
                    teleport(recipient, sender, target)
                }
                3 -> {
                    teleport(sender, sender, Location(sender.world, args[0].formatDouble(), args[1].formatDouble(), args[2].formatDouble()))
                }
                4 -> {
                    val recipient = Main.instance.playerFinder.findPlayer(args[0])
                    if (recipient == null) {
                        Chat.send(sender, "player_not_found", args[1])
                        return
                    }
                    teleport(recipient, sender, Location(recipient.world, args[1].formatDouble(), args[2].formatDouble(), args[3].formatDouble()))
                }
                else -> Chat.send(sender, "please_use", "/tp <player|location> [<player>|<location>]")
            }
        } else {
            when(args.size) {
                2 -> {
                    val recipient = Main.instance.playerFinder.findPlayer(args[0])
                    if (recipient == null) {
                        Chat.send(sender, "player_not_found", args[1])
                        return
                    }
                    val target = Main.instance.playerFinder.findPlayer(args[1])
                    if (target == null) {
                        Chat.send(sender, "player_not_found", args[1])
                        return
                    }
                    teleport(recipient, sender, target)
                }
                4 -> {
                    val recipient = Main.instance.playerFinder.findPlayer(args[0])
                    if (recipient == null) {
                        Chat.send(sender, "player_not_found", args[0])
                        return
                    }
                    teleport(recipient, sender, Location(recipient.world, args[1].formatDouble(), args[2].formatDouble(), args[3].formatDouble()))
                }
                else -> Chat.send(sender, "please_use", "/tp <player|location> [<player>|<location>]")
            }
        }
    }

    private fun teleport(player: Player, executor: CommandSender, target: Player) {
        if (player != executor) {
            Chat.send(executor, "teleport_other", player, target)
        }
        Chat.send(player, "teleport_self", target)
        lastLocationMap[player] = player.location
        player.teleport(target)
    }

    private fun teleport(player: Player, executor: CommandSender, target: Location) {
        if (player != executor) {
            Chat.send(executor, "teleport_other_location", target.x, target.y, target.z, player)
        }
        Chat.send(player, "teleport_self_location", target.x, target.y, target.z)
        lastLocationMap[player] = player.location
        player.teleport(target)
    }

    private fun String.formatDouble(): Double {
        return try {
            this.toDouble()
        } catch (e: NumberFormatException) {
            0.0
        }
    }

}