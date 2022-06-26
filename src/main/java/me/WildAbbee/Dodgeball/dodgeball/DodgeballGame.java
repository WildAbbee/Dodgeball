package me.WildAbbee.Dodgeball.dodgeball;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.WildAbbee.Dodgeball.Game;
import me.WildAbbee.Dodgeball.Utils;

public class DodgeballGame extends Game {

	private HashMap<UUID, Integer> teamOne, teamTwo;
	private Location spectatorLoc, teamOneLoc, teamTwoLoc, centerLoc;
	
	public DodgeballGame(Location spectatorLoc, Location teamOneLoc, Location teamTwoLoc, Location centerLoc) {
		this.spectatorLoc = spectatorLoc;
		this.teamOneLoc = teamOneLoc;
		this.teamTwoLoc = teamTwoLoc;
		this.centerLoc = centerLoc;
		
		this.teamOne = new HashMap<>();
		this.teamTwo = new HashMap<>();
		
		new TeamManager(this);
	}
	
	@Override
	/** Let the game know a player disconnnected. */
	public void removePlayer(Player p) {
		getPlayers().remove(p.getUniqueId());
		teamOne.remove(p.getUniqueId());
		teamTwo.remove(p.getUniqueId());
		for (UUID uuid : getPlaying()) {
			final Player player = Bukkit.getPlayer(uuid);
			player.sendMessage(Utils.color("&6DodgeBall &e" + p.getName() + " &fhas disconnected and been eliminated."));
		}
	}
	
	public HashMap<UUID, Integer> getTeamOne() {
		return this.teamOne;
	}
	
	public HashMap<UUID, Integer> getTeamTwo() {
		return this.teamTwo;
	}
	
	/** Return a list of all players who are in team one or team two. */
	public ArrayList<UUID> getPlaying() {
		final ArrayList<UUID> teams = new ArrayList<>();
		teams.addAll(teamOne.keySet());
		teams.addAll(teamTwo.keySet());
		return teams;
	}

	public Location getSpectatorLoc() {
		return spectatorLoc;
	}

	public Location getTeamOneLoc() {
		return teamOneLoc;
	}

	public Location getTeamTwoLoc() {
		return teamTwoLoc;
	}

	public Location getCenterLoc() {
		return centerLoc;
	}
}
