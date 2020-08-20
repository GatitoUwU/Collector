package me.gatogamer.collector.listeners;

import me.gatogamer.collector.Collector;
import me.gatogamer.collector.collectors.CollectorObject;
import me.gatogamer.collector.messages.MessageAPI;
import me.gatogamer.collector.utils.Utils;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener {

    @EventHandler
    public void onDrop(ItemSpawnEvent event) {
        CollectorObject collector = Collector.getInstance().getCollectorManager().getCollectors().get(event.getEntity().getLocation().getChunk());
        if (collector != null) {
            Block block = collector.getChestLocation().getBlock();
            if (block.getType() != Material.CHEST) return;
            org.bukkit.block.Chest chest = (org.bukkit.block.Chest) block.getState();

            chest.getBlockInventory().addItem(event.getEntity().getItemStack());
            event.getEntity().remove();
        }
    }

    private ItemStack collectorItem = Utils.createItem(54, 1, 0,
            Collector.getInstance().getConfig().getString("Item.Collector.name"),
            Collector.getInstance().getConfig().getStringList("Item.Collector.lore"));

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getItemInHand().isSimilar(collectorItem)) {
            Chunk chunk = event.getBlock().getLocation().getChunk();
            CollectorObject collector = Collector.getInstance().getCollectorManager().getCollectors().get(chunk);
            if (collector == null) {
                MessageAPI.sendMessage(false, false, event.getPlayer(), MessageAPI.getMessageOrDie("Actions.Put"));
                collector = new CollectorObject(event.getBlock().getLocation().getChunk(), event.getBlock().getLocation());
                Collector.getInstance().getCollectorManager().addCollector(collector);
            } else {
                MessageAPI.sendMessage(false, false, event.getPlayer(), MessageAPI.getMessageOrDie("Errors.AlreadyChunk"));
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRemove(BlockBreakEvent event) {
        Chunk chunk = event.getBlock().getLocation().getChunk();
        CollectorObject collector = Collector.getInstance().getCollectorManager().getCollectors().get(chunk);

        Block block = event.getBlock();
        if (block.getType() != Material.CHEST) return;
        org.bukkit.block.Chest chest = (org.bukkit.block.Chest) block.getState();

        if (collector != null && collector.getChestLocation().equals(event.getBlock().getLocation())) {
            MessageAPI.sendMessage(false, false, event.getPlayer(), MessageAPI.getMessageOrDie("Actions.Remove"));
            Collector.getInstance().getCollectorManager().removeCollector(collector);
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), collectorItem);
        }
    }

    @EventHandler
    public void getCollector(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/givecollector")) {
            if (event.getPlayer().hasPermission("collector.give")) {
                event.getPlayer().getInventory().addItem(collectorItem);
                MessageAPI.sendMessage(false, false, event.getPlayer(), MessageAPI.getMessageOrDie("Commands.Give"));
                event.setCancelled(true);
            }
        }
    }
}
