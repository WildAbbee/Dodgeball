package me.WildAbbee.Dodgeball;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public class DColor {
	
	public static final ArrayList<DColor> colors = new ArrayList<>();
	private static final Random random = new Random();
	/** Pick a random color! */
	public static DColor randomColor() {
		return colors.get(random.nextInt(colors.size()));
	}
	/** Pick a random color except these. May return null if all colors are excluded. */
	public static DColor randomColor(DColor... exclude) {
		for (int i = 0; i < 100; i++) {
			// cause of recursion limit ^^
			DColor color = randomColor();
			for (final DColor excludeColor : exclude) {
				if (color != null && excludeColor.equals(color)) {
					color = null;
					break;
				}
			}
			
			// no matches found
			if (color != null) return color;
		}
		return null;
	}
	
	public static final DColor RED = new DColor(Color.RED, ChatColor.RED, "Red");
	public static final DColor BLUE = new DColor(Color.BLUE, ChatColor.BLUE, "Blue");
	public static final DColor YELLOW = new DColor(Color.YELLOW, ChatColor.YELLOW, "Yellow");
	public static final DColor GREEN = new DColor(Color.GREEN, ChatColor.GREEN, "Green");

	private Color bukkitColor;
	private ChatColor chatColor;
	private String name;
	protected DColor(Color bukkitColor, ChatColor chatColor, String name) {
		super();
		this.bukkitColor = bukkitColor;
		this.chatColor = chatColor;
		this.name = name;
		colors.add(this);
	}
	public Color getBukkitColor() {
		return bukkitColor;
	}
	public ChatColor getChatColor() {
		return chatColor;
	}
	public String getName() {
		return name;
	}
}