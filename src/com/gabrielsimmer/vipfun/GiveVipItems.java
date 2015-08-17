package com.gabrielsimmer.vipfun;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveVipItems implements Listener{
	ChatColor gC = ChatColor.GOLD;

	Main plugin;
	
	public GiveVipItems(Main passedPlugin){
		this.plugin = passedPlugin;
	}
	
	List<String> wandLore = Arrays.asList("Show off a bit!", ChatColor.BOLD + "Right click me");
	List<String> runLore = Arrays.asList("Run around at superspeed!", ChatColor.BOLD + "Right click me");
	List<String> loungeLore = Arrays.asList("Warp the the VIP Lounge", ChatColor.BOLD + "Right click me");

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		
		if (player.hasPermission("vipfun.vip")){
			/* Note: DEBUG player.getInventory().clear(); */
			// --- VIP WAND ITEM ---
			ItemStack vipWandItem = new ItemStack(Material.BLAZE_ROD);
			ItemMeta vipWandMeta = vipWandItem.getItemMeta(); //GET THE META
			vipWandMeta.setDisplayName(gC + "Magic VIP Wand"); //Item name
			vipWandMeta.setLore(wandLore);
			vipWandItem.setItemMeta(vipWandMeta); //SET THE META! 
			// --- END ITEM ---

			// --- SUGAR SPEED ITEM ---
			ItemStack vipRunItem = new ItemStack(Material.SUGAR);
			ItemMeta vipRunMeta = vipRunItem.getItemMeta();
			vipRunMeta.setDisplayName(gC + "Superspeed");
			vipRunMeta.setLore(runLore);
			vipRunItem.setItemMeta(vipRunMeta);
			// --- END ITEM ---

			// --- STAIR LOUNGE ITEM ---
			ItemStack vipLoungeItem = new ItemStack(Material.BIRCH_WOOD_STAIRS);
			ItemMeta vipLoungeMeta = vipLoungeItem.getItemMeta();
			vipLoungeMeta.setDisplayName(gC + "VIP Lounge");
			vipLoungeMeta.setLore(loungeLore);
			vipLoungeItem.setItemMeta(vipLoungeMeta);
			// --- END ITEM ---
			
			int wSlot = plugin.getConfig().getInt("itemslot.wand");
			int rSlot = plugin.getConfig().getInt("itemslot.run");
			int lSlot = plugin.getConfig().getInt("itemslot.lounge");
			
			player.getInventory().setItem(wSlot, vipWandItem);
			player.getInventory().setItem(rSlot, vipRunItem);
			player.getInventory().setItem(lSlot, vipLoungeItem);
		}
	}

}
