package me.keegan.chameleon_rpg.game.mechanics;

import me.keegan.chameleon_rpg.game.player.ChameleonPlayer;
import me.keegan.chameleon_rpg.utils.classes.ChameleonHashMap;
import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import me.keegan.chameleon_rpg.utils.game.player.PlayerUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;

public final class Bits implements Listener {
    private static Bits instance;

    public static Bits getInstance() {
        if (instance == null)
            instance = new Bits();

        return instance;
    }

    private final ChameleonHashMap<EntityDeathEvent, Integer> bitMap = new ChameleonHashMap<>();
    private final HashMap<EntityDeathEvent, Double> bitMultiplierMap = new HashMap<>();

    private final int DEFAULT_BIT_REWARD = 2;
    private final int MAX_DEFAULT_BIT_REWARD = 15;

    private int getCalculatedBits(EntityDeathEvent e) {
        return (int) Math.clamp(instance.bitMap.get(e) * instance.bitMultiplierMap.getOrDefault(e, 1d), 0, Double.POSITIVE_INFINITY);
    }

    public void markEntityDeath(EntityDeathEvent e) {
        LivingEntity livingEntity = e.getEntity();
        if (livingEntity.getKiller() == null || PlayerUtils.getChameleonPlayerFromFile(livingEntity.getKiller()) == null) { return; }

        bitMap.put(e, (int) Math.clamp(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2, DEFAULT_BIT_REWARD, MAX_DEFAULT_BIT_REWARD));
    }

    public void addBits(EntityDeathEvent e, int bits) {
        bitMap.putIfExists(e, bitMap.get(e) + bits);
    }

    public void addBitMultipliers(EntityDeathEvent e, double bitMultipliers) {
        bitMultiplierMap.put(e, bitMultiplierMap.getOrDefault(e, 0d) + bitMultipliers);
    }

    @SuppressWarnings("all")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathEvent e) {
        LivingEntity livingEntity = e.getEntity();
        if (livingEntity.getKiller() == null || !instance.bitMap.containsKey(e)) { return; }

        Player killer = livingEntity.getKiller();

        int bits = getCalculatedBits(e);
        ChameleonChat.sendActionBarMessage(killer, String.format("+%d bits", bits));

        // ChameleonPlayer can not be null
        ChameleonPlayer chameleonPlayer = PlayerUtils.getChameleonPlayerFromFile(killer);
        chameleonPlayer.giveBits(bits);
        chameleonPlayer.addKills(1);

        bitMap.remove(e);
        bitMultiplierMap.remove(e);
    }
}
