package me.gatogamer.collector;

import lombok.Getter;
import lombok.Setter;
import me.gatogamer.collector.collectors.CollectorManager;
import me.gatogamer.collector.config.ConfigCreator;
import me.gatogamer.collector.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

/**
 * This code has been created by
 * gatogamer#1111 A.K.A gatogamer.
 * If you want to use my code, please
 * don't remove this message, and give
 * me the credits. Arigato! n.n
 */

@Getter
@Setter
public final class Collector extends JavaPlugin {

    private static Collector instance;
    private FileConfiguration data;
    private CollectorManager collectorManager;

    @Override
    public void onEnable() {
        instance = this;

        ConfigCreator.get().setup(this, "config");
        ConfigCreator.get().setup(this, "data");

        data = getConfigOrDie("data");

        setCollectorManager(new CollectorManager());

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);

        new BukkitRunnable() {
            @Override
            public void run() {
                ConfigCreator.get().saveConfig(Collector.getInstance(), data, "data");
            }
        }.runTaskTimer(this, 0L, 60L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Collector getInstance() {
        return instance;
    }

    public FileConfiguration getData() {
        return data;
    }

    public FileConfiguration getConfigOrDie(String configName) {
        return YamlConfiguration.loadConfiguration(new File(getDataFolder(), configName + ".yml"));
    }
}
