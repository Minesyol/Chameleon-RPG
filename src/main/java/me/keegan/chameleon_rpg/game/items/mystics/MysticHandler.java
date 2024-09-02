package me.keegan.chameleon_rpg.game.items.mystics;

import me.keegan.chameleon_rpg.game.items.mystics.types.MysticType;
import me.keegan.chameleon_rpg.utils.events.IChameleonListener;
import me.keegan.chameleon_rpg.utils.events.model.types.EntityDeathByCPlayerCEvent;
import me.keegan.chameleon_rpg.utils.game.items.MysticUtils;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public final class MysticHandler implements IChameleonListener {
    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        if (e.getResult() == null || !MysticUtils.isMystic(e.getResult())) { return; }
        e.setResult(new ItemStack(Material.AIR));
    }

    @EventHandler
    public void onPrepareItemEnchant(PrepareItemEnchantEvent e) {
        if (!MysticUtils.isMystic(e.getItem())) { return; }
        e.setCancelled(true);
    }

    public static class MysticDrops implements IChameleonListener {
        /**
         *
         * @param player Player to send heads-up to
         * @param location Location where mystic will be dropped
         */
        // TODO: implement sound and finish creating the mystics
        private void dropMystic(Player player, Location location) {
            location.add(0, 1, 0);

            ItemStack mystic = Math.random() <= 0.5
                    ? MysticFactory.createMystic(MysticType.DEFAULT, (ChatColor) MysticType.DEFAULT.getPantColors().keySet().toArray()[(new Random().nextInt(MysticType.DEFAULT.getPantColors().size() - 1))])
                    : MysticFactory.createMystic(Math.random() <= 0.5 ? MysticType.SWORD : MysticType.BOW);

            World world = player.getWorld();

            world.spawnParticle(Particle.FALLING_WATER, location, 225, 0.3, 0.26, 0.3); // i = amount
            world.dropItem(location, mystic);
        }

        @EventHandler
        public void onEntityDeathByPlayer(EntityDeathByCPlayerCEvent e) {
            dropMystic(e.getKiller(), e.getVictim().getLocation());
        }
    }

}