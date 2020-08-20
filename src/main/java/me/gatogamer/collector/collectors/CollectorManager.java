package me.gatogamer.collector.collectors;

import lombok.Getter;
import me.gatogamer.collector.Collector;
import me.gatogamer.collector.messages.MessageAPI;
import me.gatogamer.collector.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class CollectorManager {
    private Map<Chunk, CollectorObject> collectors = new HashMap<>();

    public CollectorManager() {
        FileConfiguration data = Collector.getInstance().getData();
        Set<String> dataSet = null;
        try {
            dataSet = data.getConfigurationSection("Collectors").getKeys(true);
        } catch (Exception e) {
            MessageAPI.sendMessage(true, false, "&cThere are no collectors, nothing to load.");
        }
        if (dataSet != null) {
            dataSet.forEach(collectorCfg -> {
                Location collectorLoc = Utils.stringToLocation(data.getString("Collectors."+collectorCfg));
                collectors.put(collectorLoc.getChunk(), new CollectorObject(collectorLoc.getChunk(), collectorLoc));
            });
        }
    }

    public void addCollector(CollectorObject collector) {
        Collector.getInstance().getData().set("Collectors." + collector.getChunk().getX()+"<->"+collector.getChunk().getZ(), Utils.locationToString(collector.getChestLocation()));
        collectors.put(collector.getChunk(), collector);
    }

    public void removeCollector(CollectorObject collector) {
        Collector.getInstance().getData().set("Collectors." + collector.getChunk().getX()+"<->"+collector.getChunk().getZ(), null);
        collectors.remove(collector.getChunk());
    }
}
