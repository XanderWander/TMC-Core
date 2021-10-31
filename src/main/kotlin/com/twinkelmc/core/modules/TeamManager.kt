package com.twinkelmc.core.modules

import com.twinkelmc.core.utils.LPPrefix
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class TeamManager {

    private val manager = Bukkit.getScoreboardManager()

    fun setPlayerScoreboard(p: Player) {
        val board = manager?.mainScoreboard ?: return
        var team = board.getTeam(p.uniqueId.toString().replace("-", "").substring(0, 16))
        if (team == null) {
            team = board.registerNewTeam(p.uniqueId.toString().replace("-", "").substring(0, 16))
            val color = ChatColor.getByChar(LPPrefix.getPrefix(p).substring(1, 2))
            if (color != null) team.color = color
        }
        team.addEntry(p.name)
    }

    fun removePlayerScoreboard(p: Player) {
        val board = manager?.mainScoreboard ?: return
        val team = board.getTeam(p.uniqueId.toString().replace("-", "").substring(0, 16))
        if (team != null) {
            team.removeEntry(p.name)
            team.unregister()
        }
    }

    fun resetAllTeams() {
        val board = manager?.mainScoreboard ?: return
        board.teams.forEach { it.unregister() }

        for (team in board.teams)
            team.unregister()
        for (player in PlayerModule.players) {
            setPlayerScoreboard(player)
        }
    }

}