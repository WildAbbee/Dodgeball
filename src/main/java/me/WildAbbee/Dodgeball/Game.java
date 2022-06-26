package me.WildAbbee.Dodgeball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Game {

	private HashMap<UUID, Integer> players;
	private ArrayList<UUID> specs;
	private HashSet<GameComponent> components;
	
	public Game() {
		
		this.players = Dodgeball.inst.getPlaying();
		this.specs = new ArrayList<>();
		
		for (final UUID uuid : players.keySet()) {
			final Player p = Bukkit.getPlayer(uuid);
			p.sendMessage(Utils.color("&6DodgeBall &fYou are participating with &e" + players.entrySet().size() + " &fothers."));
		}
		
		new BukkitRunnable() {
			public void run() {
				final Iterator<GameComponent> iter = components.iterator();
				while (iter.hasNext()) {
					final GameComponent comp = iter.next();
					comp.tick();
				}
			}
		}.runTaskTimer(Dodgeball.inst, 1, 1);
	}
	
	/** Let the game know a player disconnnected. */
	public void removePlayer(Player p) {
		players.remove(p.getUniqueId());
	}
	
	/** Is the player playing in this game? May not be playing at the moment. */
	public boolean isPlaying(UUID uuid) {
		return players.containsKey(uuid);
	}
	
	/** List of spectators. */
	public ArrayList<UUID> getSpecs() {
		return this.specs;
	}
	
	/** List of all participants. Some may not be playing at the moment. */
	public HashMap<UUID, Integer> getPlayers() {
		return this.players;
	}
	
	public void addComponent(GameComponent comp) {
		this.components.add(comp);
	}
}
