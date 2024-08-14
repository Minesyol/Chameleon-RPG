package me.keegan.chameleon_rpg.player;

import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.Serializable;

public final class ChameleonPlayer implements Serializable, Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ChameleonChat.sendMessage(e.getPlayer(), "Welcome");
    }
}
