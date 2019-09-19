package org.spigot.commons.messages;

import org.bukkit.entity.Player;

/**
 * A node of a message chain, holding the format and text information and a reference to the next node
 * 
 * @category Messages
 * @author MRtecno98
 *
 */
public class Message {
	/** Unformatted message text */
	private String text;
	
	/** Text format */
	private Format format;
	
	/** Child message of the chain, if null this node is the tail */
	private Message child;
	
	/**
	 * Constructs a new message using a format and a text
	 * 
	 * @param format the text format
	 * @param text the text
	 */
	public Message(Format format, String text) {
		this.text = text;
		this.format = format;
	}
	
	/**
	 * Constructs a message without using a format
	 * 
	 * @param text the message text
	 */
	public Message(String text) {
		this(new Format(), text);
	}
	
	/**
	 * Gets the child node for this message of the chain
	 * 
	 * @return the child node
	 */
	public Message getChild() {
		return child;
	}
	
	/**
	 * Sets the child node for this message of the chain
	 * 
	 * @param child another message object
	 */
	public void setChild(Message child) {
		this.child = child;
	}
	
	/**
	 * @return the text for this message
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return the format for this message
	 */
	public Format getFormat() {
		return format;
	}
	
	/**
	 * @return the formatted text just for this node
	 */
	public String getFormattedText() {
		return getFormat() + getText();
	}
	
	/**
	 * Starts a chain call of methods for all the nodes under this
	 * 
	 * @return the formatted texts for all the nodes starting by this
	 */
	public String getChainedText() {
		return getFormattedText() + (getChild() != null ? getChild().getChainedText() : "");
	}
	
	/**
	 * Sends the chained text
	 * 
	 * @param p the player to send the message to
	 */
	public void send(Player p) {
		p.sendMessage(getChainedText());
	}
}
