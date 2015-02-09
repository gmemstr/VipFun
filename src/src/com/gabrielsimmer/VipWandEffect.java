package com.gabrielsimmer;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class VipWandEffect implements Listener {
	
	@SuppressWarnings("deprecation") //Until throwSnowball() and playEffect() are changed
	@EventHandler
    public void toggle(PlayerInteractEvent event){
        Player player = event.getPlayer();
       
        if(player.getInventory().getItemInHand().getType() == Material.CARROT_STICK)
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            	player.throwSnowball();
            	player.playEffect(player.getLocation(), Effect.POTION_BREAK, 1);
            	player.playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
            	player.sendMessage(ChatColor.GOLD + "Whooosh!");
            }
        }
	}
