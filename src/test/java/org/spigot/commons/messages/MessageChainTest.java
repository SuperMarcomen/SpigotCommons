package org.spigot.commons.messages;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;
import org.spigot.commons.exceptions.SupplierNotProvidedException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Player.class)
public class MessageChainTest {
	final MessageNode msg;
	
	public MessageChainTest() {
		msg = MessageNode.link(new Message(new Format(ChatColor.AQUA), "Ciao "),
				new Placeholder("testsupplied", () -> new Message("wei i'm supplied yey ")),
				new Placeholder("testunsupplied"),
				new Message(new Format(ChatColor.RED, ChatColor.BOLD), "agagag"));
	}
	
	public MessageNode getSupplied() {
		Map<String, Supplier<MessageNode>> suppliers = new HashMap<>();
		suppliers.put("testunsupplied", () -> new Message("wei i'm not supplied "));
		
		Placeholder.supplyPlaceholders(msg, suppliers);
		
		return msg;
	}
	
	@Test
	public void suppliedChainTest() {
		final String expected = "§bCiao wei i'm supplied yey wei i'm not supplied §c§lagagag";
		
		assertEquals(getSupplied().getChainedText(), expected);
	}
	
	@Test(expected = SupplierNotProvidedException.class)
	public void unsuppliedChainTest() {
		msg.getChainedText();
	}
	
	@Test
	public void mockPlayerSendTest() {
		final String expected = "§bCiao wei i'm supplied yey wei i'm not supplied §c§lagagag";
		
		Player mockPlayer = mock(Player.class);
		
		getSupplied().send(mockPlayer);
		
		verify(mockPlayer).sendMessage(expected);
	}
}
