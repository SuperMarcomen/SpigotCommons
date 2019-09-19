package org.spigot.commons.messages;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Player.class)
public class MessageChainTest {
	final Message msg;
	final String expected = "§bCiao §c§lagagag";
	
	public MessageChainTest() {
		msg = new Message(new Format(ChatColor.AQUA), "Ciao ");
		msg.setChild(new Message(new Format(ChatColor.RED, ChatColor.BOLD), "agagag"));
	}
	
	@Test
	public void messageChainTest() {
		assertEquals(msg.getChainedText(), "§bCiao §c§lagagag");
	}
	
	@Test
	public void mockPlayerSendTest() {
		Player mockPlayer = mock(Player.class);
		
		msg.send(mockPlayer);
		
		verify(mockPlayer).sendMessage(expected);
	}
}
