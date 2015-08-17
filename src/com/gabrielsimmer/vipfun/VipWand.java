package com.gabrielsimmer.vipfun;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class VipWand implements CommandExecutor {
		List<String> wandLore = Arrays.asList("Show off a bit!", "Right click me");
		Main plugin;
		ChatColor gC = ChatColor.GOLD; //Basically shortens ChatColor.GOLD to gC for easier access.
		
		public VipWand(Main passedPlugin){
			this.plugin = passedPlugin;
		}
		
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
			Player player = (Player) sender;
			
			if(!player.hasPermission("vipfun.wand")){
				player.sendMessage(ChatColor.RED + "Sorry, you're not VIP! Use /buy to buy it!");
			}
			
			else if (player.hasPermission("vipfun.wand")){
			
			player.sendMessage(gC + "Enjoy the magical VIP wand!");
			ItemStack vipWandItem = new ItemStack(Material.CARROT_STICK); //Custom resource pack needs to make this kickass
			ItemMeta vipWandMeta = vipWandItem.getItemMeta(); //GET THE META
			vipWandMeta.setDisplayName(gC + "VIP Magic Wand"); //Item name
			vipWandMeta.setLore(wandLore);
			vipWandItem.setItemMeta(vipWandMeta); //SET THE META! 
			
			/*
			 * GET THE META
			 * SET THE META
			 * etc. Testing
			 */
			
			player.getInventory().addItem(vipWandItem); //Give player the item
			
			}
			
			return false;
		}
		
}