package me.WildAbbee.Dodgeball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.WildAbbee.Dodgeball.commands.StartGameCommand;
import me.WildAbbee.Dodgeball.listeners.OnJoinListener;
import me.WildAbbee.Dodgeball.listeners.OnQuitListener;

public class Dodgeball extends JavaPlugin {

	/*
	 * TODO:
	 * Spawn balls
	 * Teleport players to correct areas
	 * Detect player killing another player
	 * Detect ball missing
	 * Brackets system
	 */
	
	private Game game;
	private ArrayList<UUID> notPlaying;
	
	public static Dodgeball inst;
	
	public void onEnable() {
		inst = this;
		
		saveDefaultConfig();
		
		notPlaying = new ArrayList<>();
		
		getCommand("startgame").setExecutor(new StartGameCommand());
		
		final PluginManager pm = Bukkit.getPluginManager();

		pm.registerEvents(new OnJoinListener(), this);
		pm.registerEvents(new OnQuitListener(), this);
	}
	
	/** Gets a HashMap of all online player's uuid's excluding those that are not playing. Value is 0, this is used in Game to store the score. */
	public HashMap<UUID, Integer> getPlaying() {
		final HashMap<UUID, Integer> playing = new HashMap<>();
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (notPlaying.contains(p.getUniqueId())) continue;
			playing.put(p.getUniqueId(), 0);
		}
		return playing;
	}
	
	public Game getGame() {
		return game;
	}

	public void startGame(Game game) {
		this.game = game;
	}
}
