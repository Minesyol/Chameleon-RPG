package me.keegan.chameleon_rpg.game.player;

import me.keegan.chameleon_rpg.game.mechanics.Bits;
import me.keegan.chameleon_rpg.utils.game.player.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.Serial;
import java.io.Serializable;

public final class ChameleonPlayer implements Serializable, Listener {
    @Serial
    private static final long serialVersionUID = 5345345L;

    private transient Player player;

    private int kills;
    private int bits;
    private int totalBits;

    public ChameleonPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getKills() {
        return kills;
    }

    public void addKills(int kills) {
        this.kills += kills;
    }

    public int getBits() {
        return bits;
    }

    public void giveBits(int bits) {
        this.bits += bits;
        addTotalBits(Math.clamp(bits, 0, Integer.MAX_VALUE));
    }

    public int getTotalBits() {
        return totalBits;
    }

    private void addTotalBits(int totalBits) {
        this.totalBits += totalBits;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeath(EntityDeathEvent e) {
        Bits.getInstance().markEntityDeath(e);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        ChameleonPlayer chameleonPlayer = PlayerUtils.getChameleonPlayerFromFile(e.getPlayer());
        if (chameleonPlayer == null) { return; }

        PlayerUtils.saveChameleonPlayerToFile(chameleonPlayer);
        PlayerUtils.removeChameleonPlayerFromCache(chameleonPlayer);
    }
}
