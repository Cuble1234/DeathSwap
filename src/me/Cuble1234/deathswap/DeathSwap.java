package me.Cuble1234.deathswap;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin implements Listener{
	
	Player players[] = new Player[2];
	private Random r = new Random();
	int random;
	private boolean start = false;
	private int taskID;
	private Player lose;

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "");
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("ds")) {
			if (!(args.length == 1)) {
				p.sendMessage(ChatColor.RED + "/ds start");
				return true;
			}
			
			if (!args[0].equalsIgnoreCase("start")) {
				p.sendMessage(ChatColor.RED + "/ds start");
				return true;
			}
			
			deathSwap();
			
		}
		return true;
	}
	
	public void deathSwap() {
		start = true;
		
		int i = 0;
		for(Player player : Bukkit.getServer().getOnlinePlayers()) {
			players[i] = player;
			i++;
		}
		Location l1 = players[0].getLocation();
		Location l2 = players[0].getLocation();
		l1.add(708, 0, 708);
		l2.add(-708, 0, -708);
		for(int y = 0; y<257; y++) {
			l1.setY(y);
			if(l1.getBlock().getType() == Material.AIR) {
				l1.setY(y+1);
				break;
			}
		}
		for(int y = 0; y<257; y++) {
			l2.setY(y);
			if(l2.getBlock().getType() == Material.AIR) {
				break;
			}
		}
		players[0].teleport(l1);
		players[1].teleport(l2);
		random = r.nextInt((int) (5*60*20 - 3*60*20))+3*60*20;
		
		 taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if(start) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(DeathSwap.this, new Runnable() {
						public void run() {
							Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Teleporting in 3 seconds!");
						}
					}, (long) (random - 60));
					Location lp1 = players[0].getLocation();
					Location lp2 = players[1].getLocation();
					players[0].teleport(lp2);
					players[1].teleport(lp1);
					random = r.nextInt((int) (5*60*20 - 3*60*20))+3*60*20;
				}		
			}
		}, (long) (random) , (long) (random));
		 
		 
		
	}
	
	@EventHandler
	public void onPlayerDeath(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if(!start) return;
		Player p = (Player) e.getEntity();
		if(p.getHealth()<= e.getDamage()) {
			start = false;
			Bukkit.getServer().getScheduler().cancelTask(taskID);
			lose = p;
			p.setGameMode(GameMode.SPECTATOR);
			
			 if(players[0].getName().equalsIgnoreCase(lose.getName())) {
				 players[1].sendMessage(ChatColor.GREEN + "You are the winner");
			 }
			 else {
				 players[0].sendMessage(ChatColor.GREEN + "You are the winner");
			 }
		}
	}
	
	
}
