package com.gabrielsimmer.vipfun;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemEffects implements Listener {

	Random rn = new Random();
	
	Main plugin;
	ChatColor gC = ChatColor.GOLD; //Basically shortens ChatColor.GOLD to gC for easier access.
	
	public ItemEffects(Main passedPlugin){
		this.plugin = passedPlugin;
	}

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

		// --- SPEED EFFECT ---
		if(player.getInventory().getItemInHand().getType() == Material.SUGAR){
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
				PotionEffect speed = PotionEffectType.SPEED.createEffect(300, 1);
				player.addPotionEffect(speed, true);
			}
		}
		// --- END EFFECT ---

		// --- VIP LOUNGE EFFECT ---
		if(player.getInventory().getItemInHand().getType() == Material.BIRCH_WOOD_STAIRS){
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
				
				player.sendMessage(gC + "Teleporting to VIP Lounge...");
				 //Load co-ordinates and world from config.yml!
				int loungeX = plugin.getConfig().getInt("viplounge.x");
				int loungeY = plugin.getConfig().getInt("viplounge.y");
				int loungeZ = plugin.getConfig().getInt("viplounge.z");
				String loungeWorld = plugin.getConfig().getString("viplounge.world");
				
				player.teleport(new Location(Bukkit.getWorld(loungeWorld), loungeX, loungeY, loungeZ));
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

			for (int i=0;i<100;i++){
				Location loc = new Location(landedWorld, landedX + rn.nextInt() % 2, landedY + rn.nextInt() % 2, landedZ + rn.nextInt() % 2);

				landedWorld.playEffect(loc, Effect.COLOURED_DUST, 12, 10);
				landedWorld.playEffect(loc, Effect.HAPPY_VILLAGER, 12);
				landedWorld.playEffect(loc, Effect.PORTAL, 12);
				landedWorld.playEffect(loc, Effect.CLOUD, 12);
			}
		}
	}
}