package me.Cuble1234.deathswap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class DeathSwap extends JavaPlugin {

	
	@Override
	public void onEnable() {
		SettingsManager.getInstance().setup(this);
		if(SettingsManager.getInstance().getConfig().getInt("maxtime") < SettingsManager.getInstance().getConfig().getInt("mintime")) {
			System.out.println("Max number cannot be smaller than the min nember!");
			this.getPluginLoader().disablePlugin(this);
		}
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
		DeathSwapCommand dsc = new DeathSwapCommand();
		getCommand("ds").setExecutor(dsc);
		
	}
	
}
