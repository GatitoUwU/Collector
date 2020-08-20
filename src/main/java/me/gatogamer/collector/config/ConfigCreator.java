package me.gatogamer.collector.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * This code has been created by
 * gatogamer#1111 A.K.A gatogamer.
 * If you want to use my code, please
 * don't remove this message, and give
 * me the credits. Arigato! n.n
 */

public class ConfigCreator {

    private static ConfigCreator instance;
    private File pluginDir;
    private File configFile;

    public static ConfigCreator get() {
        if (instance == null) {
            instance = new ConfigCreator();
        }
        return instance;
    }

    public void setup(Plugin p, String configname) {
        this.pluginDir = p.getDataFolder();
        this.configFile = new File(this.pluginDir, configname + ".yml");
        if (!this.configFile.exists()) {
            p.saveResource(configname + ".yml", false);
        }
    }

    public void saveFile(Plugin p, String filename) {
        this.pluginDir = p.getDataFolder();
        this.configFile = new File(this.pluginDir, filename);
        if (!this.configFile.exists()) {
            p.saveResource(filename, false);
        }
    }

    public void saveConfig(Plugin p, FileConfiguration config, String fileName) {
        File configFile = new File(p.getDataFolder(), fileName+".yml");
        try {
            config.save(configFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}