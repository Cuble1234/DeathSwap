package me.Cuble1234.deathswap;

import org.bukkit.Location;
import org.bukkit.entity.Player;


public class VariableManager {
	
	private VariableManager() { }

	private static VariableManager instance = new VariableManager();

	public static VariableManager getInstance() {
		return instance;
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
