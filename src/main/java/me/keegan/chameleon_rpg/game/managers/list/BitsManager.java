package me.keegan.chameleon_rpg.game.managers.list;

import lombok.Getter;
import me.keegan.chameleon_rpg.game.managers.ChameleonManager;
import me.keegan.chameleon_rpg.game.player.ChameleonPlayer;
import me.keegan.chameleon_rpg.utils.objects.classes.ChameleonHashMap;
import me.keegan.chameleon_rpg.utils.events.model.types.EntityDeathByCPlayerCEvent;
import me.keegan.chameleon_rpg.utils.game.ChameleonChat;
import me.keegan.chameleon_rpg.utils.game.entity.player.PlayerUtils;
import me.keegan.chameleon_rpg.utils.objects.classes.singleton.StaticInstance;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.HashMap;

public final class BitsManager extends ChameleonManager {
    private static final ChameleonHashMap<EntityDeathByCPlayerCEvent, Integer> bitMap = new ChameleonHashMap<>();
    private static final HashMap<EntityDeathByCPlayerCEvent, Double> bitMultiplierMap = new HashMap<>();

    private final int DEFAULT_BIT_REWARD = 2;
    private final int MAX_DEFAULT_BIT_REWARD = 15;

    @Getter
    @StaticInstance
    private static BitsManager instance;

    private BitsManager() {}

    private int getCalculatedBits(EntityDeathByCPlayerCEvent e) {
        return (int) Math.clamp(bitMap.get(e) * bitMultiplierMap.getOrDefault(e, 1d), 0, Double.POSITIVE_INFINITY);
    }

    public void markEntityDeath(EntityDeathByCPlayerCEvent e) {
        bitMap.put(e, (int) Math.clamp(e.getVictim().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 2, DEFAULT_BIT_REWARD, MAX_DEFAULT_BIT_REWARD));
    }

    public void addBits(EntityDeathByCPlayerCEvent e, int bits) {
        bitMap.putIfExists(e, bitMap.get(e) + bits);
    }

    public void addBitMultipliers(EntityDeathByCPlayerCEvent e, double bitMultipliers) {
        bitMultiplierMap.put(e, bitMultiplierMap.getOrDefault(e, 0d) + bitMultipliers);
    }

    @SuppressWarnings("all")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDeath(EntityDeathByCPlayerCEvent e) {
        LivingEntity livingEntity = e.getVictim();
        if (!bitMap.containsKey(e)) { return; }

        Player killer = e.getKiller();

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
