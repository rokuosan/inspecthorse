package com.deviseworks.inspecthorse.commands

import com.deviseworks.inspecthorse.App
import com.deviseworks.inspecthorse.common.Store
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender

class InspectCommand(plugin: App): CommandExecutor {
    private val logger = plugin.logger

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // Console using is not allowed
        if(sender is ConsoleCommandSender) return true

        // Permission check
        if(!sender.hasPermission("inspecthorse.inspect.use")){
            sender.sendMessage("権限がありません")
            return true
        }

        // Command actions
        if(command.name == "inspect"){
            this.run(sender)
            return true
        }

        // End
        return false
    }

    private fun run(sender: CommandSender){
        // Define player
        val player = sender.server.getPlayer(sender.name)
        if(player == null) {
            logger.warning("Error: Player is null")
            return
        }else{
            player.sendMessage("馬を右クリックすることで、速度を表示します。")
            Store.inspectWaitingList.add(player.uniqueId.toString())
        }
    }
}