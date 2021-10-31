package com.twinkelmc.core.commands.commands

import com.twinkelmc.core.Main
import com.twinkelmc.core.chat.Chat
import org.bukkit.Bukkit
import org.bukkit.WeatherType
import org.bukkit.attribute.Attribute
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import java.lang.NumberFormatException

class Utility {

    fun commandPTime(sender: CommandSender, args: ArrayList<String>) {
        if (args.size == 0) {
            Chat.send(sender, "please_use", "/ptime [time] <player>")
            return
        }
        if (args.size == 1) {
            if (sender is Player) {
                Chat.send(sender, "time_change_self", args[0])
                try {
                    val time = args[0].toLong()
                    sender.setPlayerTime(time, false)
                } catch (e: NumberFormatException) {
                    when {
                        args[0].contains("day") -> sender.setPlayerTime(6000L, false)
                        args[0].contains("night") -> sender.setPlayerTime(18000L, false)
                        else -> sender.setPlayerTime(6000L, false)
                    }
                }
            } else {
                Chat.send(sender, "please_use", "/ptime [time] [player]")
            }
        } else {
            val target = Main.instance.playerFinder.findPlayer(args[1])
            if (target == null) {
                Chat.send(sender, "player_not_found", args[1])
            } else {
                try {
                    val time = args[0].toLong()
                    target.setPlayerTime(time, false)
                } catch (e: NumberFormatException) {
                    when {
                        args[0].contains("day") -> target.setPlayerTime(6000L, false)
                        args[0].contains("night") -> target.setPlayerTime(18000L, false)
                        else -> target.setPlayerTime(6000L, false)
                    }
                }
                Chat.send(target, "time_change_self", args[0])
                if (target != sender) {
                    Chat.send(sender, "time_change_other", args[0], target)
                }
            }
        }
    }

    fun commandPWeather(sender: CommandSender, args: ArrayList<String>) {
        if (args.size == 0) {
            Chat.send(sender, "please_use", "/ptime [time] <player>")
            return
        }
        if (args.size == 1) {
            if (sender is Player) {
                if (args[0].contains("rain") || args[0].contains("downfall")) {
                    sender.setPlayerWeather(WeatherType.DOWNFALL)
                    Chat.send(sender, "weather_change_self", "rain")
                } else {
                    sender.setPlayerWeather(WeatherType.CLEAR)
                    Chat.send(sender, "weather_change_self", "clear")
                }
            } else {
                Chat.send(sender, "please_use", "/ptime [time] <player>")
            }
        } else {
            val target = Main.instance.playerFinder.findPlayer(args[1])
            if (target == null) {
                Chat.send(sender, "player_not_found", args[1])
            } else {
                if (args[0].contains("rain") || args[0].contains("downfall")) {
                    target.setPlayerWeather(WeatherType.DOWNFALL)
                    Chat.send(target, "weather_change_self", "rain")
                    if (target != sender) {
                        Chat.send(sender, "weather_change_other", target, "rain")
                    }
                } else {
                    target.setPlayerWeather(WeatherType.CLEAR)
                    Chat.send(target, "weather_change_self", "clear")
                    if (target != sender) {
                        Chat.send(sender, "weather_change_other", target, "clear")
                    }
                }
            }
        }
    }

    fun commandHeal(sender: CommandSender, args: ArrayList<String>) {
        if (args.size == 0) {
            if (sender is Player) {
                sender.health = sender.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.defaultValue ?: 20.0
                for (effect in sender.activePotionEffects)
                    sender.removePotionEffect(effect.type)
                Chat.send(sender, "heal_self")
            } else {
                Chat.send(sender, "please_use", "/heal [player]")
            }
        } else {
            val target = Main.instance.playerFinder.findPlayer(args[0])
            if (target == null) {
                Chat.send(sender, "player_not_found", args[0])
            } else {
                target.health = target.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.defaultValue ?: 20.0
                target.foodLevel = 20
                for (effect in target.activePotionEffects)
                    target.removePotionEffect(effect.type)
                Chat.send(target, "heal_self")
                if (target != sender) {
                    Chat.send(sender, "heal_other", target)
                }
            }
        }
    }

    fun commandCraft(sender: Player, args: ArrayList<String>) {
        sender.openWorkbench(null, true)
    }

    fun commandInvsee(sender: Player, args: ArrayList<String>) {
        val target = Main.instance.playerFinder.findPlayer(args[0])
        if (target == null) {
            Chat.send(sender, "player_not_found", args[0])
        } else {
            sender.openInventory(target.inventory)
        }
    }

}
