package me.Cuble1234.deathswap;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin {
	
	private Random r = new Random();
	private int random;
	
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
	}
	
	public void deathSwap() {
		
		VariableManager vm = VariableManager.getInstance();
		
		vm.setStart(true);
		
		Location l1 = vm.getPlayers()[0].getLocation();
		Location l2 = vm.getPlayers()[0].getLocation();
		vm.setStartLocation(vm.getPlayers()[0].getLocation());
		
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
		
		vm.getPlayers()[0].teleport(l1);
		vm.getPlayers()[1].teleport(l2);
		
		random = r.nextInt((int) (5 * 60 * 20 - 3 * 60 * 20)) + 3 * 60 * 20;

		vm.setTaskID(Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				if (vm.isStart()) {
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(DeathSwap.this, new Runnable() {
						public void run() {
							Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "Teleporting in 3 seconds!");
						}
					}, (long) (random - 60));
					
					Location lp1 = vm.getPlayers()[0].getLocation();
					Location lp2 = vm.getPlayers()[1].getLocation();
					vm.getPlayers()[0].teleport(lp2);
					vm.getPlayers()[1].teleport(lp1);
					
					random = r.nextInt((int) (5 * 60 * 20 - 3 * 60 * 20)) + 3 * 60 * 20;
				}
			}
		}, (long) (random), (long) (random)));
	}
}
