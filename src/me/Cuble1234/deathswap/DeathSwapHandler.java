package me.Cuble1234.deathswap;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

public class DeathSwapHandler {
	
	private Random r = new Random();
	private int random;

	public void deathSwap() {
		
		SettingsManager sm = SettingsManager.getInstance();
		
		int max = 20 * sm.getConfig().getInt("maxtime");
		int min = 20 * sm.getConfig().getInt("mintime");
		sm.setStart(true);
		
		Location l1 = sm.getPlayers()[0].getLocation(new Location(sm.getPlayers()[0].getWorld(),r.nextInt(100000-1000)+1000, 0,r.nextInt(100000-1000)+1000));
		Location l2 = l1;
		sm.setStartLocation(sm.getPlayers()[0].getLocation());
		
		l1.add(708, 0, 708);
		l2.add(-708, 0, -708);
		
		for (int y = 0; y < 257; y++) {
			l1.setY(y);
			if (l1.getBlock().getType() == Material.AIR) {
				l1.setY(y + 1);
				break;
			}
		}
		
		for (int y = 0; y < 257; y++) {
			l2.setY(y);
			if (l2.getBlock().getType() == Material.AIR) {
				l2.setY(y+1);
				break;
			}
		}
		
		sm.getPlayers()[0].teleport(l1);
		sm.getPlayers()[1].teleport(l2);
		
		random = (int) (r.nextInt((int) (max - min)) + min);
		
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(sm.getPlugin(), new Runnable() {
			public void run() {
				if(sm.isStart()) {
					Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Teleporting in 3 seconds!");
				}
			}
		}, (long) (random - 60));

		sm.setTaskID(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(sm.getPlugin(), new Runnable() {
			public void run() {
				if (sm.isStart()) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(sm.getPlugin(), new Runnable() {
						public void run() {
							if(sm.isStart()) {
								Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Teleporting in 3 seconds!");
								random = (int) (r.nextInt((int) (max - min)) + max);
							}
						}
					}, (long) (random - 60));
					
					Location lp1 = sm.getPlayers()[0].getLocation();
					Location lp2 = sm.getPlayers()[1].getLocation();
					sm.getPlayers()[0].teleport(lp2);
					sm.getPlayers()[1].teleport(lp1);
					
				}
			}
		}, (long) (random), (long) (random)));
	}
}
