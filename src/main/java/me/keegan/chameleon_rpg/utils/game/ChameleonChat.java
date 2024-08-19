package me.keegan.chameleon_rpg.utils.game;

import me.keegan.chameleon_rpg.ChameleonRPG;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public final class ChameleonChat {
    private static final String MESSAGE_PREFIX = ChatColor.DARK_GREEN + "Chameleon>" + ChatColor.GRAY;

    private static String addMessagePrefix(String message) {
        return String.format("%s %s", MESSAGE_PREFIX, message);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(addMessagePrefix(message));
    }

    public static void sendActionBarMessage(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public static void sendBroadcast(String message) {
        ChameleonRPG.getPlugin().getServer().broadcastMessage(addMessagePrefix(message));
    }
}
