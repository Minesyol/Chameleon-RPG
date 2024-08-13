package me.keegan.chameleon_rpg.player;

import me.keegan.chameleon_rpg.utils.ChameleonChat;
import me.keegan.chameleon_rpg.utils.TaskScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.Serializable;

public class ChameleonPlayer implements Serializable, Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ChameleonChat.sendMessage(e.getPlayer(), "Hello buddy...");
        TaskScheduler.scheduleTask(() -> ChameleonChat.sendBroadcast("THIS IS A BROADCAST"), 60, false);
    }
}
