package me.WildAbbee.Dodgeball.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.WildAbbee.Dodgeball.Dodgeball;
import me.WildAbbee.Dodgeball.dodgeball.DodgeballGame;

public class StartGameCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (Dodgeball.inst.getGame() != null) {
			sender.sendMessage(ChatColor.DARK_RED + "Whoooooooooooopsies! " + ChatColor.RED + "A game is already running!");
			return true;
		}
		
		sender.sendMessage(ChatColor.GREEN + "Started game successfully. (well maybe there was an error, I don't actually know at this point)");
		Dodgeball.inst.startGame(new DodgeballGame((Location) Dodgeball.inst.getConfig().get("locations.spectator"),
				(Location) Dodgeball.inst.getConfig().get("locations.teamOne"),
				(Location) Dodgeball.inst.getConfig().get("locations.teamTwo"),
				(Location) Dodgeball.inst.getConfig().get("locations.center")));

		return true;
	}
}
