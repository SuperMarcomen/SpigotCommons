package org.spigot.commons.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.spigot.commons.SpigotCommons;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YAMLConfiguration extends Configuration {

    private final File file;
    private FileConfiguration configuration;

    public YAMLConfiguration(String name, SpigotCommons lib) {
        file = new File(lib.getPlugin().getDataFolder(), name + ".yml");

        if (!file.exists())
            lib.getPlugin().saveResource(name + ".yml", false);

        reload();
    }

    @Override
    public boolean saveConfiguration() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            //TODO Use the log system.
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean reload() {
        configuration = YamlConfiguration.loadConfiguration(file);
        return true;
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return configuration.getBoolean(path);
    }

    @Override
    public Integer getInteger(String path) {
        return configuration.getInt(path);
    }

    @Override
    public List<Integer> getIntegerList(String path) {
        return configuration.getIntegerList(path);
    }

    @Override
    public Long getLong(String path) {
        return configuration.getLong(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return configuration.getLongList(path);
    }

    @Override
    public Short getShort(String path) {
        return (short) configuration.getInt(path);
    }

    @Override
    public List<Short> getShortList(String path) {
        return configuration.getShortList(path);
    }

    @Override
    public Double getDouble(String path) {
        return configuration.getDouble(path);
    }

    @Override
    public List<Double> getDoubleList(String path) {
        return configuration.getDoubleList(path);
    }

    @Override
    public Float getFloat(String path) {
        return Float.parseFloat(getString(path));
    }

    @Override
    public List<Float> getFloatList(String path) {
        return configuration.getFloatList(path);
    }

    @Override
    public List<String> getKeys(String path) {
        return new ArrayList<>(configuration.getConfigurationSection(path).getKeys(false));
    }

    @Override
    public Object get(String path) {
        return configuration.get(path);
    }

    public FileConfiguration asBukkitConfig() {
        return configuration;
    }
}
