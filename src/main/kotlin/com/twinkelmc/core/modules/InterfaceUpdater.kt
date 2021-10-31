package com.twinkelmc.core.modules

import com.twinkelmc.core.Main
import org.bukkit.scheduler.BukkitRunnable

class InterfaceUpdater: BukkitRunnable() {

    fun start() {
        this.runTaskTimer(Main.instance, 0L, 20L)
    }

    override fun run() {
        TODO("Not yet implemented")
    }

}