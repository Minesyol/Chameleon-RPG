package me.keegan.chameleon_rpg.game.managers.list.damage;

import lombok.Getter;
import me.keegan.chameleon_rpg.game.managers.ChameleonManager;
import me.keegan.chameleon_rpg.utils.events.model.types.LivingEntityDamageByLivingEntityCEvent;
import me.keegan.chameleon_rpg.utils.objects.classes.singleton.StaticInstance;
import org.bukkit.event.EventHandler;

import java.util.HashMap;

public final class DamageManager extends ChameleonManager {
    private static final HashMap<LivingEntityDamageByLivingEntityCEvent, LivingEntityDamageProfile> damageProfiles = new HashMap<>();

    @Getter
    @StaticInstance
    private static DamageManager instance;

    private DamageManager() {}

    public LivingEntityDamageProfile getDamageProfile(LivingEntityDamageByLivingEntityCEvent e) {
        damageProfiles.putIfAbsent(e, new LivingEntityDamageProfile(e.getVictim()));
        return damageProfiles.get(e);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLivingEntityDamageByLivingEntity(LivingEntityDamageByLivingEntityCEvent e) {
        // todo
    }
}