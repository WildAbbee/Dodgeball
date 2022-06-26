package me.WildAbbee.Dodgeball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.WildAbbee.Dodgeball.Dodgeball;
import me.WildAbbee.Dodgeball.Game;
import me.WildAbbee.Dodgeball.Utils;

public class OnJoinListener implements Listener {

	@EventHandler
	void onJoin(PlayerJoinEvent e) {
		if (Dodgeball.inst.getGame() == null) {
			e.setJoinMessage(Utils.color("&6&lDodgeBall &e" + e.getPlayer().getName() + "&f has joined."));
		} else {
			final Game game = Dodgeball.inst.getGame();
			game.getSpecs().add(e.getPlayer().getUniqueId());
			e.setJoinMessage(null);
		}
	}
}
	