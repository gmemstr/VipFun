package com.gabrielsimmer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class VipLounge implements CommandExecutor{

	VipFun plugin;
	ChatColor gC = ChatColor.GOLD; //Basically shortens ChatColor.GOLD to gC for easier access.
	
	public VipLounge(VipFun passedPlugin){
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		
		if(!player.hasPermission("vipfun.vip")){
			player.sendMessage(ChatColor.RED + "Sorry, you're not VIP! Use /buy to buy it!");
		}
		
		else if (player.hasPermission("vipfun.lounge")){
		
		player.sendMessage(gC + "Teleporting to VIP Lounge...");
		 //Load co-ordinates and world from config.yml!
		int loungeX = plugin.getConfig().getInt("viplounge.x");
		int loungeY = plugin.getConfig().getInt("viplounge.y");
		int loungeZ = plugin.getConfig().getInt("viplounge.z");
		String loungeWorld = plugin.getConfig().getString("viplounge.world");
		
		player.teleport(new Location(Bukkit.getWorld(loungeWorld), loungeX, loungeY, loungeZ)); //Todo: Add /vipsetlounge
		
		}
		
		return false;
	}
}
