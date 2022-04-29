package com.deviseworks.inspecthorse.events

import com.deviseworks.inspecthorse.common.Store
import org.bukkit.ChatColor
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType
import org.bukkit.entity.Horse
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class RightClickHorseEvent: Listener {
    @EventHandler
    fun onRightClick(e: PlayerInteractEntityEvent){
        if(!Store.inspectWaitingList.contains(e.player.uniqueId.toString())) return

        val targetEntity = e.rightClicked

        if(targetEntity.type == EntityType.HORSE ||
            targetEntity.type == EntityType.SKELETON_HORSE ||
                targetEntity.type == EntityType.ZOMBIE_HORSE){
            val horse = targetEntity as Horse
            val name = horse.customName
            val speed =horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.value?.times(100)
            val jump = horse.jumpStrength.times(100)

            e.player.sendMessage("${ChatColor.AQUA}${ChatColor.STRIKETHROUGH} |                                     | ")
            if(!name.isNullOrEmpty()) e.player.sendMessage("名前: $name")
            e.player.sendMessage("スピード: ${ChatColor.YELLOW}${ChatColor.ITALIC}%,.2f".format(speed))
            e.player.sendMessage("ジャンプ: ${ChatColor.YELLOW}${ChatColor.ITALIC}%,.2f".format(jump))
            e.player.sendMessage("${ChatColor.AQUA}${ChatColor.STRIKETHROUGH} |                                     | ")

        }else{
            e.player.sendMessage("馬ではありません。")
        }

        Store.inspectWaitingList.remove(e.player.uniqueId.toString())
    }
}