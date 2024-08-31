package me.keegan.chameleon_rpg.game.items.mystics;

import me.keegan.chameleon_rpg.utils.game.items.MysticUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

public class MysticHandler implements Listener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        if (e.getResult() == null || !MysticUtils.isMystic(e.getResult())) { return; }
        e.setResult(new ItemStack(Material.AIR));
    }
}
