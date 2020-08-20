package me.gatogamer.collector.collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Chunk;
import org.bukkit.Location;

@Getter
@Setter
@AllArgsConstructor
public class CollectorObject {
    private Chunk chunk;
    private Location chestLocation;
}
