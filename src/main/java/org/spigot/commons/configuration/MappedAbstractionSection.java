package org.spigot.commons.configuration;

import org.bukkit.configuration.ConfigurationSection;

public abstract class MappedAbstractionSection extends AbstractionSection {
	private String[] keys;
	
	public MappedAbstractionSection(ConfigurationSection sect, Object... preCloningData) {
		super(sect, preCloningData);
		postCloning(preCloningData);
	}
	
	@Override
	public void preCloning(Object... data) {
		registerKeys(data);
		if(keys == null) keys = new String[0];
	}
	
	@Override
	public void set(String key, Object value) {
		super.set(key, value);
		for(String keyword : keys) if(key.equals(keyword)) onKeywordSet(key, value);
	}
	
	public abstract void onKeywordSet(String key, Object value);
	
	public void registerKeys(Object... data) {}
	public void postCloning(Object... data) {}
	protected void setKeys(String... keys) {
		this.keys = keys;
	}
}
