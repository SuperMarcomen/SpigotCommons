package org.spigot.commons.messages;

public class Message extends MessageNode implements Formatted {
	/** Unformatted message text */
	private String text;
	
	/** Text format */
	private Format format;
	
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

	@Override
	public String getText() {
		return getFormat() + text;
	}

	@Override
	public Format getFormat() {
		return format;
	}
}
