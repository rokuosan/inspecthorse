package com.deviseworks.inspecthorse

import org.bukkit.plugin.java.JavaPlugin

class App: JavaPlugin() {
    override fun onEnable() {
        logger.info("Inspect Horse is ready.")
    }
}