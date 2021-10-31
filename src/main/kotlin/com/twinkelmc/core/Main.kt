package com.twinkelmc.core

import com.twinkelmc.core.chat.Chat
import net.luckperms.api.LuckPerms
import com.twinkelmc.core.commands.CommandHandler
import com.twinkelmc.core.events.ChatEvent
import com.twinkelmc.core.events.ConnectionEvent
import com.twinkelmc.core.events.ExplosionEvent
import com.twinkelmc.core.events.PlayerHealth
import com.twinkelmc.core.modules.ClassInstance
import com.twinkelmc.core.modules.PlayerModule
import com.twinkelmc.core.modules.TeamManager
import com.twinkelmc.core.utils.MessageFormatter
import com.twinkelmc.core.utils.PlayerFinder
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main: JavaPlugin() {

    companion object {
        lateinit var instance: Main
    }

    val classInstance = ClassInstance()
    val messageFormatter = MessageFormatter()
    val teamManager = TeamManager()
    val playerFinder = PlayerFinder()
    var rp: String = "https://www.dropbox.com/s/zd4qoddvhozb2lz/TwinkelMC201.16.3v4.zip?dl=1"
    var api: LuckPerms? = null

    override fun onEnable() {

        instance = this

        val provider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)
        if (provider != null)
            api = provider.provider

        PlayerModule.register(Bukkit.getOnlinePlayers())
        teamManager.resetAllTeams()
        Chat.loadMessages()

        loadResourcePack()
        registerEvents()
        registerCommands()

        logger.info("TMC-Core enabled.")

    }

    override fun onDisable() {

        logger.info("TMC-Core disabled.")

    }

    private fun loadResourcePack() {
        try {
            val yml = YamlConfiguration.loadConfiguration(File("plugins/TMC-Core/rp.yml"))
            rp = yml.getString("rp") ?: rp
        } catch (e: Exception) {}
    }

    private fun registerEvents() {

        Bukkit.getPluginManager().registerEvents(ConnectionEvent(), this)
        Bukkit.getPluginManager().registerEvents(ChatEvent(), this)
        Bukkit.getPluginManager().registerEvents(PlayerHealth(), this)
        Bukkit.getPluginManager().registerEvents(ExplosionEvent(), this)

    }

    private fun registerCommands() {

        for (command in this.description.commands) {
            val c = this.getCommand(command.key)
            if (c != null && c.isRegistered) {
                c.setExecutor(CommandHandler())
            }
        }

    }

}