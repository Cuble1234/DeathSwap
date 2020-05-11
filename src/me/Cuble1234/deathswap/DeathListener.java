package me.Cuble1234.deathswap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DeathListener implements Listener{
	
	SettingsManager sm = SettingsManager.getInstance();
	@EventHandler
	public void onPlayerDeath(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!sm.isStart()) return;
		
		Player p = (Player) e.getEntity();
		
		if (p.getHealth() <= e.getDamage()) {
			sm.setStart(false);
			Bukkit.getServer().getScheduler().cancelTask(sm.getTaskID());
			Player lose = p;
			e.setCancelled(true);
			p.teleport(sm.getStartLocation());
			p.setHealth(20);

			if (sm.getPlayers()[0].getName().equalsIgnoreCase(lose.getName())) {
				sm.getPlayers()[1].sendMessage(ChatColor.GREEN + "You are the winner");
				sm.getPlayers()[1].teleport(sm.getStartLocation());
			}
			else {
				sm.getPlayers()[0].sendMessage(ChatColor.GREEN + "You are the winner");
				sm.getPlayers()[0].teleport(sm.getStartLocation());
			}
			
			sm.setPlayers(null,0);
			sm.setPlayers(null,1);
		}
	}
}
