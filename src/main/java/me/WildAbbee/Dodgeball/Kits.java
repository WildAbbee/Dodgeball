package me.WildAbbee.Dodgeball;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Kits {
	
	public static void setTeam(Player p, DColor c) {
		p.setGameMode(GameMode.ADVENTURE);
		
		final PlayerInventory inventory = p.getInventory();
		inventory.clear();
		
		// armour
		inventory.setBoots(new ItemStack(Material.LEATHER_BOOTS));
		inventory.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
		inventory.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
		inventory.setHelmet(new ItemStack(Material.LEATHER_HELMET));
		
		for (final ItemStack item : inventory.getArmorContents()) {
			final LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
			meta.setColor(c.getBukkitColor());
			meta.setDisplayName(c.getChatColor() + Utils.toTitleCase(c.toString()) + "'s " + Utils.toTitleCase(item.getType().toString().replace("LEATHER_", "")));
			meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			item.setItemMeta(meta);
		}
	}
	
	public static void setSpec(Player p) {
		p.setGameMode(GameMode.ADVENTURE);
		
		final PlayerInventory inventory = p.getInventory();
		inventory.clear();
	}
	
	public static ItemStack getBall() {
		final ItemStack ball = new ItemStack(Material.BOW);
		ball.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
		final ItemMeta ballMeta = ball.getItemMeta();
		ballMeta.setDisplayName(ChatColor.RESET + "Dodgeball");
		ballMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		ball.setItemMeta(ballMeta);
		return ball;
	}
}
