package com.ystoreplugins.yscreenshare.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.base.Charsets;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigUtils {

    private final File file;
    private final FileConfiguration config;

    public ConfigUtils(JavaPlugin plugin, String name) {
        file = new File(plugin.getDataFolder(), name);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(name, false);
        }

        config = new YamlConfiguration();

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public FileConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }
    
    public void save() {
    	try {
    		config.save(file);
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
