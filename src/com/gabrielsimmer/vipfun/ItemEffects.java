package com.gabrielsimmer.vipfun;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemEffects implements Listener {
	
	@SuppressWarnings("deprecation") //Until throwSnowball() and playEffect() are changed
	@EventHandler
    public void rightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        
        // --- MAGIC VIP WANT EFFECT ---
        if(player.getInventory().getItemInHand().getType() == Material.BLAZE_ROD){
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            	player.throwSnowball();
            }
        }
        // --- END EFFECT ---
	}
	
	@EventHandler
	public void thrownBall(ProjectileHitEvent e){
		if (e.getEntity() instanceof Snowball){
			double landedX = e.getEntity().getLocation().getBlockX();
			double landedY = e.getEntity().getLocation().getBlockY();
			double landedZ = e.getEntity().getLocation().getBlockZ();
			World landedWorld = e.getEntity().getLocation().getWorld();
			
			 Location loc = new Location(landedWorld, landedX, landedY, landedZ);
			 e.getEntity().getLocation().getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 51);
		}
	}
}