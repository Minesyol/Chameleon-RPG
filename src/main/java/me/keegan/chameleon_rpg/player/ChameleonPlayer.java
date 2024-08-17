package me.keegan.chameleon_rpg.player;

import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import me.keegan.chameleon_rpg.utils.game.player.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.Serializable;

public final class ChameleonPlayer implements Serializable, Listener {
    private transient Player player;

    public ChameleonPlayer () {}

    public ChameleonPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ChameleonPlayer chameleonPlayer = PlayerUtils.getChameleonPlayerFromFile(e.getPlayer());
        if (chameleonPlayer == null) {
            ChameleonChat.sendBroadcast("The chameleon player is null");
            return;
        }

        //ChameleonChat.sendBroadcast(chameleonPlayer.cookie + " cookies");
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        PlayerUtils.saveChameleonPlayerToFile(this);
    }
}
