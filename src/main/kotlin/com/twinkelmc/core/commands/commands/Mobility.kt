package com.twinkelmc.core.commands.commands

import com.twinkelmc.core.Main
import com.twinkelmc.core.chat.Chat
import com.twinkelmc.core.chat.EnabledState
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.lang.NumberFormatException
import kotlin.math.max
import kotlin.math.min

class Mobility {

    fun commandUp(player: Player) {
        val loc = player.location
        var state = 0
        for (i in loc.y.toInt()..256) {
            when(state) {
                0 -> { if (!loc.block.isPassable) state = 1 }
                1 -> { if ( loc.block.isPassable) state = 2 }
                2 -> { if (loc.block.isPassable) { state = 3; break } else { state = 1 } }
            }
            loc.y++
        }
        if (state == 3) {
            Chat.send(player, "teleport_up")
            player.teleport(loc.add(0.0, -1.0, 0.0))
        } else {
            Chat.send(player, "no_floor_above")
        }
    }

    fun commandTop(player: Player) {
        val loc = player.location
        loc.y = 256.0
        var state = false
        for (i in player.location.y.toInt()..256) {
            if (!loc.block.isPassable) { state = true; break }
            loc.y--
        }
        if (state && loc.y.toInt() != player.location.y.toInt()) {
            Chat.send(player, "teleport_top")
            player.teleport(loc.add(0.0, 1.0, 0.0))
        } else {
            Chat.send(player, "no_floor_above")
        }
    }

    fun commandFly(sender: CommandSender, args: ArrayList<String>) {
        if (args.size > 0) {
            val player = Main.instance.playerFinder.findPlayer(args[0])
            if (player == null) {
                Chat.send(sender, "player_not_found", args[0])
                return
            }
            setFly(sender, player)
        } else {
            if (sender is Player) { setFly(sender, sender) }
            else { sender.sendMessage("Please use /fly [player]") }
        }
    }

    private fun setFly(sender: CommandSender, receiver: Player) {
        receiver.allowFlight = !receiver.allowFlight
        if (receiver.allowFlight) {
            receiver.velocity = receiver.velocity.setY(0.001)
            val bukkitRunnable = object : BukkitRunnable() { override fun run() { receiver.isFlying = true } }
            bukkitRunnable.runTaskLater(Main.instance, 1)
        }
        if (sender != receiver) {
            Chat.send(sender, "fly_change_other", receiver, EnabledState(receiver.allowFlight))
        }
        Chat.send(receiver, "fly_change_self", EnabledState(receiver.allowFlight))

    }

    fun commandSpeed(sender: CommandSender, args: ArrayList<String>) {
        if (args.size == 2) {
            val receiver = Main.instance.playerFinder.findPlayer(args[1])
            if (receiver == null) {
                Chat.send(sender, "player_not_found", args[1])
                return
            }
            setSpeed(executor = sender, receiver = receiver, args[0])
        } else if (sender is Player && args.size != 0) { setSpeed(executor = sender, receiver = sender, args[0])
        } else { Chat.send(sender, "please_use", "/speed [speed] [player]") }
    }

    private fun setSpeed(executor: CommandSender, receiver: Player, speed: String) {
        var speedNum = 0.0F
        try{ speedNum = speed.toFloat() }
        catch (e: NumberFormatException) { executor.sendMessage("Unknown speed value $speed") }
        if (receiver.isFlying) {
            receiver.flySpeed = max(min(speedNum/10, 1.0F),-1.0F)
            if (executor !is Player || executor != receiver) {
                Chat.send(executor, "fly_speed_other", receiver, receiver.flySpeed.toDouble()*10.0)
            }
            Chat.send(executor, "fly_speed_self", receiver.flySpeed.toDouble()*10.0)
        } else {
            receiver.walkSpeed = max(min(speedNum/5, 1.0F),-1.0F)
            if (executor !is Player || executor != receiver) {
                Chat.send(executor, "walk_speed_other", receiver, receiver.walkSpeed.toDouble()*5.0)
            }
            Chat.send(executor, "walk_speed_self", receiver.walkSpeed.toDouble()*5.0)
        }

    }

}