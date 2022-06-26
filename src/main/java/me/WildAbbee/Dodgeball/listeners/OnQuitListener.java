package me.WildAbbee.Dodgeball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.WildAbbee.Dodgeball.Dodgeball;

public class OnQuitListener implements Listener {

	@EventHandler
	void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		if (Dodgeball.inst.getGame() == null) return;
		Dodgeball.inst.getGame().removePlayer(e.getPlayer());
	}
}
