package io.github.qhenckel.sleepyTime;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SleepyTime extends JavaPlugin implements Listener{

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sleepytime")) {
			if(sender instanceof Player){
				Player player = (Player) sender;
				Bukkit.broadcastMessage("Sleeping: " +sleep(player, 0));
				return true;
			} else {
				sender.sendMessage("you must be a player");
			}
		} 
		return false; 
	}
	
	public String sleep(Player sleepP, int start){
		Player[] players = Bukkit.getOnlinePlayers();
	    int req = players.length/2;
	    if(req < 2) req = 1;
		int sleeping = start;
		for( Player p : players){
			if(p.isSleeping()){
				sleeping += 1;
			}
		}
		if(sleeping >= players.length/2){
			sleepP.getLocation().getWorld().setTime(1000);
		}
		return sleeping + "/" + req;
	}
	
	@EventHandler
	public void onSleep(PlayerBedEnterEvent e) {
	    Bukkit.broadcastMessage(e.getPlayer().getName() + " is now sleeping. " + sleep(e.getPlayer(), 1));
	}
}
