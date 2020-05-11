package me.Cuble1234.deathswap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class DeathSwap extends JavaPlugin {

	
	@Override
	public void onEnable() {
		SettingsManager.getInstance().setup(this);
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
		DeathSwapCommand dsc = new DeathSwapCommand();
		getCommand("ds").setExecutor(dsc);
		
		
	}
	
}
