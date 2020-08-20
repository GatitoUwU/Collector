package me.gatogamer.collector.messages;

import me.gatogamer.collector.Collector;
import me.gatogamer.collector.utils.Entry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This code has been created by
 * gatogamer#1111 A.K.A gatogamer.
 * If you want to use my code, please
 * don't remove this message, and give
 * me the credits. Arigato! n.n
 */

public class MessageAPI {

    public static void sendMessage(int pluginMessage, int debug, CommandSender commandSender, String message) {
        sendMessage((pluginMessage == 1), (pluginMessage == 1), commandSender, message);
    }

    public static void sendMessage(int pluginMessage, int debug, Player player, String message) {
        sendMessage((pluginMessage == 1), (pluginMessage == 1), player, message);
    }

    public static void sendMessage(int pluginMessage, int debug, String message) {
        sendMessage((pluginMessage == 1), (pluginMessage == 1), message);
    }

    public static void sendMessage(boolean pluginMessage, boolean debug, CommandSender commandSender, String message) {
        if (debug) {
            if (Collector.getInstance().getConfig().getBoolean("debug")) {
                commandSender.sendMessage(returnMessage(pluginMessage, debug, message));
            }
        } else {
            commandSender.sendMessage(returnMessage(pluginMessage, debug, message));
        }
    }

    public static void sendMessage(boolean pluginMessage, boolean debug, Player player, String message) {
        if (debug) {
            if (Collector.getInstance().getConfig().getBoolean("debug")) {
                player.sendMessage(returnMessage(pluginMessage, debug, message));
            }
        } else {
            player.sendMessage(returnMessage(pluginMessage, debug, message));
        }
    }

    public static void sendMessage(boolean pluginMessage, boolean debug, String message) {
        if (debug) {
            if (Collector.getInstance().getConfig().getBoolean("debug")) {
                Bukkit.getConsoleSender().sendMessage(returnMessage(pluginMessage, debug, message));
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(returnMessage(pluginMessage, debug, message));
        }
    }

    private static String returnMessage(boolean pluginMessage, boolean debug, String message) {
        return c((pluginMessage ? "&8[&cCollector!&8] &f" : "") + (debug ? "&cDebug -> &f" : "") + message);
    }

    public static String getMessageOrDie(String param) {
        return Collector.getInstance().getConfigOrDie("config").getString(param);
    }

    public static String getMessageOrDie(String param, Entry... entries) {
        List<Entry> entriesList = Arrays.asList(entries);
        String text = getMessageOrDie(param);
        for (Entry entry : entriesList) {
            text = text.replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }
        return text;
    }

    public static String c(String c) {
        return ChatColor.translateAlternateColorCodes('&', c);
    }
    
}
