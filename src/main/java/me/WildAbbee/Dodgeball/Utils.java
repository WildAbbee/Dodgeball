package me.WildAbbee.Dodgeball;

import org.bukkit.ChatColor;

public class Utils {

	//https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string
	public static String toTitleCase(String givenString) {
	    String[] arr = givenString.split(" ");
	    StringBuffer sb = new StringBuffer();

	    for (int i = 0; i < arr.length; i++) {
	        sb.append(Character.toUpperCase(arr[i].charAt(0)))
	            .append(arr[i].substring(1)).append(" ");
	    }          
	    return sb.toString().trim();
	}  
	
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
