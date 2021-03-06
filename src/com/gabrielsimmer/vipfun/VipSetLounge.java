package com.gabrielsimmer.vipfun;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VipSetLounge implements CommandExecutor {

	Main plugin;
	ChatColor gC = ChatColor.GOLD; //Basically shortens ChatColor.GOLD to gC for easier access.
	
	public VipSetLounge(Main passedPlugin){
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		
		if(!player.hasPermission("vipfun.admin")){
			player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
		}
		
		else if (player.hasPermission("vipfun.admin")){
		
		player.sendMessage(gC + "Setting lounge location...");
		int newLoungeX = player.getLocation().getBlockX();
		int newLoungeY = player.getLocation().getBlockY();
		int newLoungeZ = player.getLocation().getBlockZ();
		String newLoungeWorld = player.getWorld().getName();
		
		plugin.getConfig().set("viplounge.x", newLoungeX);
		plugin.getConfig().set("viplounge.y", newLoungeY);
		plugin.getConfig().set("viplounge.z", newLoungeZ);
		plugin.getConfig().set("viplounge.world", newLoungeWorld);
		
	    plugin.saveConfig();
		
	    player.sendMessage(gC + "Done!");
		
		}
		
		return false;
	}
}
