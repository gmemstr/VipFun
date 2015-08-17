package com.gabrielsimmer.vipfun;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VipRun implements CommandExecutor{

	Main plugin;
	ChatColor gC = ChatColor.GOLD; //Basically shortens ChatColor.GOLD to gC for easier access.
	
	public VipRun(Main passedPlugin){
		this.plugin = passedPlugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = (Player) sender;
		
		if(!player.hasPermission("vipfun.run")){
			player.sendMessage(ChatColor.RED + "Sorry, you're not VIP! Use /buy to buy it!");
		}
		
		else if (player.hasPermission("vipfun.run")){
		
		player.sendMessage(gC + "Enjoy the speed boost!");
		
		PotionEffect speed = PotionEffectType.SPEED.createEffect(2400, 1); //Create speed effect
		
		player.addPotionEffect(speed, true); //Give player effect
		
		}
		
		return false;
	}
}
