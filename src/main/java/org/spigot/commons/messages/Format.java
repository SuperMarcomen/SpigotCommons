package org.spigot.commons.messages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.bukkit.ChatColor;

/**
 * An extension of an {@link ArrayList} of {@link ChatColor}s representing a format combination
 * 
 * @author MRtecno98
 */
public class Format extends ArrayList<ChatColor> {
	private static final long serialVersionUID = 6895307663337940033L;
	
	/** Color char for this format, default ChatColor.COLOR_CHAR */
	private char colorChar = ChatColor.COLOR_CHAR;
	
	/**
	 * @see ArrayList#ArrayList()
	 */
	public Format() {
		super();
	}
	
	/**
	 * @see ArrayList#ArrayList(Collection)
	 * 
	 * @param c start format
	 */
	public Format(Collection<? extends ChatColor> c) {
		super(c);
	}
	
	/**
	 * Similar to {@link Format#Format(Collection)}, but with varargs instead of a {@link Collection}
	 * 
	 * @param formats
	 */
	public Format(ChatColor... formats) {
		super(Arrays.asList(formats));
	}
	
	/**
	 * @see ArrayList#ArrayList(int)
	 * 
	 * @param initialCapacity
	 */
	public Format(int initialCapacity) {
		super(initialCapacity);
	}
	
	/**
	 * Sets the color char to be used to parse the format
	 * 
	 * @param c the color char
	 */
	public void setColorChar(char c) {
		this.colorChar = c;
	}
	
	/**
	 * Gets the color char that will be used to parse the format
	 * 
	 * @return the color char
	 */
	public char getColorChar() {
		return colorChar;
	}
	
	/**
	 * Converts the format object to an ordered string containing 
	 * the {@link ChatColor}s, according to the Format {@link ArrayList} order.
	 * 
	 * This overrides the {@link Object} {@link Object#toString()} method
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		String format = "";
		for(ChatColor s : this) format+=s.toString();
		return format.replace(ChatColor.COLOR_CHAR, getColorChar());
	}
}
