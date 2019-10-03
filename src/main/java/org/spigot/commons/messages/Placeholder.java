package org.spigot.commons.messages;

import java.util.Map;
import java.util.function.Supplier;

import org.spigot.commons.exceptions.SupplierNotProvidedException;

public class Placeholder extends MessageNode implements Formatted {
	private Format f;
	private String name;
	private Supplier<MessageNode> supplier;
	
	public Placeholder(String name, Format f, Supplier<MessageNode> supplier) {
		this.name = name;
		this.f = f;
		this.supplier = supplier;
	}
	
	public Placeholder(String name, Supplier<MessageNode> supplier) {
		this(name, new Format(), supplier);
	}
	
	public Placeholder(String name, Format f) {
		this(name, f, null);
	}
	
	public Placeholder(String name) {
		this(name, new Format(), null);
	}

	@Override
	public String getText() {
		return getFormat() + getSupplier().get().getText();
	}

	@Override
	public Format getFormat() {
		return f;
	}
	
	public String getName() {
		return name;
	}
	
	protected void setSupplier(Supplier<MessageNode> supplier) {
		this.supplier = supplier;
	}
	
	public Supplier<MessageNode> getSupplier() {
		if(supplier == null) throw new SupplierNotProvidedException(this);
		return supplier;
	}
	
	public static void supplyPlaceholders(MessageNode head, Map<String, Supplier<MessageNode>> suppliers) {
		for(MessageNode node : head) {
			if(node instanceof Placeholder) {
				Placeholder ph = ((Placeholder) node);
				if(suppliers.containsKey(ph.getName())) 
					ph.setSupplier(suppliers.get(ph.getName()));
			}
		}
	}

}
