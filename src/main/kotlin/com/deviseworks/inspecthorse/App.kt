package com.deviseworks.inspecthorse

import com.deviseworks.inspecthorse.commands.InspectCommand
import com.deviseworks.inspecthorse.events.RightClickHorseEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class App: JavaPlugin() {
    override fun onEnable() {
        getCommand("inspect")?.setExecutor(InspectCommand(this))
        Bukkit.getServer().pluginManager.registerEvents(RightClickHorseEvent(), this)
        logger.info("Inspect Horse is ready.")
    }
}