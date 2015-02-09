/*
 * This java file is the main java file that has:
 * a) The help text
 * b) Senders to java files with command info stuff
 * c) Todolist
 * d) Comments
 * 
 * 
 * The entire thing it pretty straightforward.
 * 
 * Todo:
 * Right-clicking VIP Magic Wand fires particle stream
 * 
 * vipfun.vip is the permission for PEX/whatever
 */

package com.gabrielsimmer;

//Imports
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class VipFun extends JavaPlugin implements Listener{
	
	public void loadConfiguration(){
		//Config defaults
		this.getConfig().addDefault("viplounge.x", 0);
		this.getConfig().addDefault("viplounge.y", 120);
		this.getConfig().addDefault("viplounge.z", 0);
		this.getConfig().addDefault("viplounge.world", "world");
		
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
		getServer().getPluginManager().registerEvents(new VipWandEffect(), this); //Right-click with VIP wand
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
				player.sendMessage(ChatColor.RED + "Sorry, you're not VIP! Use /buy to buy it!");
			}
			else if (player.hasPermission("vipfun.vip")){
				
			player.sendMessage(gC + "VipFun Help");
			player.sendMessage(gC + "----"); //COLOURS!
			player.sendMessage(gC + "/vipfun - print out the help."); //Done
			player.sendMessage(gC + "/vipwand - get the magical VIP wand!"); //Done, functionality in progress.
			player.sendMessage(gC + "/viprun - grants temporary speed in the lobby!"); //Done
			player.sendMessage(gC + "/viplounge - teleport to the special VIP lounge."); //Done.
			
			}
			
			return true;

		}
		
		return false;
	}
}
