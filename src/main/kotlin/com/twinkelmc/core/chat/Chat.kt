package com.twinkelmc.core.chat

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.round

class Chat {

    companion object {
        private val hexPattern = Pattern.compile("<#([a-fA-F0-9]{6})>")
        private val messages = hashMapOf<String, HashMap<String, String>>()

        private const val prefix = "<#bf2e2e><#bold>Twinkel<#bf2e2e>MC"
        private const val delimiter = "<#4f4f4f>▪"
        private const val chat = "<#b8b8b8>"
        private const val args = "<#ed9e15>"

        fun loadMessages() {

            messages["nl"] = MessageLoader().loadNL()

        }

        fun send(player: CommandSender, message: String, vararg args: Any) {
            try {
                val msg = generateMessage(player, message, *args)
                player.sendMessage(messageColorize("$prefix $delimiter $chat$msg"))
            } catch (e: Exception) {
                player.sendMessage("Internal error. ${e.message} ${e.stackTrace}")
            }
        }

        fun get(player: CommandSender, message: String, vararg args: Any): String {
            return try {
                generateMessage(player, message, *args)
            } catch (e: Exception) {
                "Internal error. ${e.message} ${e.stackTrace}"
            }
        }

        private fun generateMessage(player: CommandSender, message: String, vararg args: Any): String {
            var msg = messageData(PlayerLanguage.language(player), message)
            for (arg in args) {
                val type = arg.javaClass.name.lowercase().split(".").lastOrNull() ?: "string"

                msg = when(type) {
                    "craftplayer", "commandsender" -> formatArg(msg, "{player}", (arg as Player).name)
                    "enabledstate" -> formatArg(msg, "{string}", (arg as EnabledState).toLang(PlayerLanguage.language(player)))
                    "double" -> formatArg(msg, "{double}", round(arg as Double).toString())
                    else -> formatArg(msg, "{$type}", arg.toString())
                }
            }
            return msg
        }

        private fun formatArg(message: String, key: String, arg: String): String {
            return message.replaceFirst(key, "$args$arg$chat")
        }

        private fun messageData(lang: String, message: String): String {
            return messages
                .getOrDefault(lang, hashMapOf(Pair(lang, "Language not supported.")))
                .getOrDefault(message, "Message not supported.")
        }

        private fun messageColorize(message: String): String {
            var msg = message
            var matcher: Matcher = hexPattern.matcher(message)
            while (matcher.find()) {
                val hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length - 1))
                val before = msg.substring(0, matcher.start())
                val after = msg.substring(matcher.end())
                msg = before + hexColor + after
                matcher = hexPattern.matcher(msg)
            }
            msg = msg.replace("<#bold>", "${ChatColor.BOLD}")
            return msg
        }

    }

}