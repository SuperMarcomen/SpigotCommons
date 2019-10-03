package org.spigot.commons.messages;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import com.google.common.collect.Lists;

/**
 * A node of a message chain, holding the format and text information and a reference to the next node
 * 
 * @category Messages
 * @author MRtecno98
 *
 */
public abstract class MessageNode implements Iterable<MessageNode> {
	/** Child message of the chain, if null this node is the tail */
	private MessageNode prev, next;
	
	/**
	 * Sets the child node for this message of the chain
	 * 
	 * @param child another message object
	 */
	protected void setNextNode(MessageNode next) {
		this.next = next;
	}
	
	protected void setPreviousNode(MessageNode prev) {
		this.prev = prev;
	}
		
	/**
	 * Gets the next node for this message of the chain
	 * 
	 * @return the next node
	 */
	public MessageNode getNextNode() {
		return next;
	}
		
	public MessageNode getPreviousNode() {
		return prev;
	}
    
    public boolean isHead() {
        return getPreviousNode() == null;
    }
    
    public boolean isTail() {
        return getNextNode() == null;
    }
    
    public MessageNode getTail() {
        return isTail() ? this : getNextNode().getTail();
    }
    
    public MessageNode getHead() {
        return isHead() ? this : getPreviousNode().getHead();
    }
    
    public void addNext(MessageNode node) {
    	MessageNode tail = node.getTail();
        getNextNode().setPreviousNode(tail);
		tail.setNextNode(getNextNode());
        
		MessageNode head = node.getHead();
		head.setPreviousNode(this);
        setNextNode(head);
	}
    
    public static MessageNode link(MessageNode first, MessageNode... rest) {
        List<MessageNode> lnodes = Lists.asList(first, rest);
		for(int i = 0; i < lnodes.size(); i++) {
			MessageNode node = lnodes.get(i);
			node.setNextNode(i == lnodes.size()-1 ? null : lnodes.get(i+1));
			node.setPreviousNode(i == 0 ? null : lnodes.get(lnodes.size()-1));
		}
        
        return lnodes.get(0);
   }
	
	/**
	 * @return the text for this message
	 */
	public abstract String getText();
	
	/**
	 * Starts a chain call of methods for all the nodes under this
	 * 
	 * @return the formatted texts for all the nodes starting by this
	 */
	public String getChainedText() {
		return getText() + (getNextNode() != null ? getNextNode().getChainedText() : "");
	}
	
	@Override
	public String toString() {
		return getChainedText();
	}
	
	/**
	 * Sends the chained text
	 * 
	 * @param p the player to send the message to
	 */
	public void send(Player p) {
		p.sendMessage(getChainedText());
	}
	
	public void broadcast() {
		Bukkit.getOnlinePlayers().forEach((p) -> send(p));
	}
	
	public void authorizedBroadcast(Permission permission) {
		Bukkit.getOnlinePlayers().forEach((p) -> {
			if(p.hasPermission(permission)) send(p);
		});
	}
	
	@Override
	public Iterator<MessageNode> iterator() {
		return new MessageIterator(this);
	}
	
	class MessageIterator implements Iterator<MessageNode> {
		private MessageNode head;
		
		public MessageIterator(MessageNode head) {
			this.head = head;
		}
		
		@Override
		public boolean hasNext() {
			return head.getNextNode() != null;
		}

		@Override
		public MessageNode next() {
			return head = head.getNextNode();
		}
	}
}
