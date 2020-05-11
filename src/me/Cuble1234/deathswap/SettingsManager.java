package me.Cuble1234.deathswap;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SettingsManager {
	 
    private SettingsManager() { }
   
    static SettingsManager instance = new SettingsManager();
   
    public static SettingsManager getInstance() {
            return instance;
    }
   
    Plugin p;
    FileConfiguration config;
    File cfile;
   
    public void setup(Plugin p) {
            config = p.getConfig();
            config.options().copyDefaults(true);
            cfile = new File(p.getDataFolder(), "config.yml");
            saveConfig();
            this.p = p;
    }
   
    public FileConfiguration getConfig() {
            return config;
    }
   
    public void saveConfig() {
            try {
                    config.save(cfile);
            }
            catch (IOException e) {
                    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
            }
    }
    
	public Plugin getPlugin() {
		return p;
	}
	public void setPlugin(Plugin p) {
		this.p = p;
	}

	private Player players[] = new Player[2];
	private Location startLocation;
	private boolean start = false;
	private int taskID;

	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player p, int i) {
		this.players[i] = p;
	}
	public Location getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}	
}
