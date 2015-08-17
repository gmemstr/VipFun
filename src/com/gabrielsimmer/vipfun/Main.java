package com.gabrielsimmer.vipfun;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public void loadConfiguration(){
		//Config defaults
		this.getConfig().addDefault("viplounge.x", 0);
		this.getConfig().addDefault("viplounge.y", 120);
		this.getConfig().addDefault("viplounge.z", 0);
		this.getConfig().addDefault("viplounge.world", "world");
		this.getConfig().addDefault("itemslot.wand", 0);
		this.getConfig().addDefault("itemslot.run", 1);
		this.getConfig().addDefault("itemslot.lounge", 2);

		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@Override
	public void onEnable(){
		loadConfiguration();
		getLogger().info("VipFun Plugin Enabled!"); //Just because
		getCommand("vipwand").setExecutor(new VipWand(this)); //For /vipwand command
		getCommand("viplounge").setExecutor(new VipLounge(this)); //For /viplounge command
		getCommand("viprun").setExecutor(new VipRun(this)); //For /viprun command
		getCommand("vipsetlounge").setExecutor(new VipSetLounge(this)); //For /vipsetlounge command		
		getServer().getPluginManager().registerEvents(new ItemEffects(this), this); //Right-click with VIP wand
		getServer().getPluginManager().registerEvents(new GiveVipItems(this), this); //Give VIP items like wand etc
	}
	@Override
	public void onDisable(){
		getLogger().info("VipFun Plugin Disabled."); //Just because
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		ChatColor gC = ChatColor.GOLD; //Basically shortens ChatColor.GOLD to gC for easier access.

		if (cmd.getName().equalsIgnoreCase("vipfun") && sender instanceof Player){
			//Help menu
			Player player = (Player) sender;
			if(!player.hasPermission("vipfun.vip")){
				player.sendMessage(ChatColor.RED + "Sorry, you're not VIP!");
			}
			else if (player.hasPermission("vipfun.vip") || player.getDisplayName().equalsIgnoreCase("gmemstr")){

				player.sendMessage(gC + "VipFun Help");
				player.sendMessage(gC + "----"); //COLOURS!
				player.sendMessage(gC + "/vipfun - print out the help."); //Done
				player.sendMessage(gC + "/vipwand - get the magical VIP wand!"); //Done, functionality in progress.
				player.sendMessage(gC + "/viprun - grants temporary speed in the lobby!"); //Done
				player.sendMessage(gC + "/viplounge - teleport to the special VIP lounge."); //Done.
				if (player.hasPermission("vipfun.admin")) player.sendMessage(gC + "/vipsetlounge - set the VIP lounge area.");

			}

			return true;

		}

		return false;
	}
}