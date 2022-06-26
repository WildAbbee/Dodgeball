package me.WildAbbee.Dodgeball.dodgeball;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.WildAbbee.Dodgeball.DColor;
import me.WildAbbee.Dodgeball.GameComponent;
import me.WildAbbee.Dodgeball.Utils;

public class TeamManager implements GameComponent {

	private DColor teamOneColor, teamTwoColor;

	private DodgeballGame game;

	public TeamManager(DodgeballGame game) {
		this.game = game;
		this.chooseColors();
	}

	/*
	 * lets hope we dont get the (1/Color.colors.size())^100*100% chance that we get
	 * a null color
	 */
	private void chooseColors() {
		this.teamOneColor = DColor.randomColor();
		this.teamTwoColor = DColor.randomColor(this.teamOneColor);
	}

	@Override
	public void tick() {
		if (game.getTeamOne().size() < 1) {
			fightEnded(true);
		} else if (game.getTeamTwo().size() < 1) {
			fightEnded(false);
		}

	}

	/** A team was defeated! */
	@SuppressWarnings("deprecation") // not gonna update soo
	private void fightEnded(boolean teamOneLost) {
		final HashMap<UUID, Integer> lost = (teamOneLost ? game.getTeamOne() : game.getTeamTwo());
		final HashMap<UUID, Integer> won = (teamOneLost ? game.getTeamTwo() : game.getTeamTwo());

		if (teamOneLost) {
			Bukkit.broadcastMessage(Utils.color("&6DodgeBall " + teamTwoColor.getChatColor() + teamTwoColor.getName()
					+ " Team &fhave defeated " + teamOneColor.getChatColor() + teamOneColor.getName() + " Team&f!"));
		} else {
			Bukkit.broadcastMessage(Utils.color("&6DodgeBall " + teamOneColor.getChatColor() + teamOneColor.getName()
					+ " Team &fhave defeated " + teamTwoColor.getChatColor() + teamTwoColor.getName() + " Team&f!"));
		}

		// losing team
		for (final Entry<UUID, Integer> entry : lost.entrySet()) {
			final UUID uuid = entry.getKey();
			final int pointsThisGame = entry.getValue();
			final int totalPoints = game.getPlayers().get(uuid);
			final Player player = Bukkit.getPlayer(uuid);
			player.sendMessage(Utils.color("&6DodgeBall &fYour team lost and you have been eliminated. &4:("));
			player.sendMessage(Utils
					.color("&6DodgeBall &fYou gained &e" + pointsThisGame + " &fpoints bringing your total up to &e"
							+ totalPoints + " &fpoints." + (totalPoints >= 10 ? " &a&oThat's a lot of points!" : "")));
			player.sendTitle(Utils.color("&c&lYOU LOST!"), "");
		}

		// winning team
		for (final Entry<UUID, Integer> entry : won.entrySet()) {
			final UUID uuid = entry.getKey();
			final int pointsThisGame = entry.getValue();
			final int totalPoints = game.getPlayers().get(uuid);
			final Player player = Bukkit.getPlayer(uuid);
			player.sendMessage(Utils.color("&6DodgeBall &fYour team won and you will move on to the next round."));
			player.sendMessage(Utils
					.color("&6DodgeBall &fYou gained &e" + pointsThisGame + " &fpoints bringing your total up to &e"
							+ totalPoints + " &fpoints." + (totalPoints >= 10 ? " &a&oThat's a lot of points!" : "")));

			player.sendTitle(Utils.color("&a&lYOU WON!"), "");
		}

		game.getTeamOne().clear();
		game.getTeamTwo().clear();
	}
}
