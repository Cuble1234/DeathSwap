package me.Cuble1234.deathswap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DeathListener implements Listener{
	
	VariableManager vm = VariableManager.getInstance();
	@EventHandler
	public void onPlayerDeath(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!vm.isStart()) return;
		
		Player p = (Player) e.getEntity();
		
		if (p.getHealth() <= e.getDamage()) {
			vm.setStart(false);
			Bukkit.getServer().getScheduler().cancelTask(vm.getTaskID());
			Player lose = p;

			if (vm.getPlayers()[0].getName().equalsIgnoreCase(lose.getName())) {
				vm.getPlayers()[1].sendMessage(ChatColor.GREEN + "You are the winner");
				vm.getPlayers()[1].teleport(vm.getStartLocation());
			}
			
			else {
				vm.getPlayers()[0].sendMessage(ChatColor.GREEN + "You are the winner");
				vm.getPlayers()[0].teleport(vm.getStartLocation());
			}

			e.setCancelled(true);
			p.setHealth(20);

			vm.setPlayers(null,0);
			vm.setPlayers(null,1);
		}
	}
}
