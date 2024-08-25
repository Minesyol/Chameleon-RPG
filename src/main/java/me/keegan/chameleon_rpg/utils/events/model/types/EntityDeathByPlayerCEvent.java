package me.keegan.chameleon_rpg.utils.events.model.types;

import me.keegan.chameleon_rpg.utils.events.model.ChameleonEvent;
import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class EntityDeathByPlayerCEvent extends ChameleonEvent {
    private final LivingEntity livingEntity;
    private final Player player;

    public EntityDeathByPlayerCEvent(@NonNull LivingEntity livingEntity, @NonNull Player player) {
        this.livingEntity = livingEntity;
        this.player = player;
    }

    public LivingEntity getVictim() {
        return livingEntity;
    }

    public Player getKiller() {
        return player;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) { return; }
        ChameleonEventService.callEvent(new EntityDeathByPlayerCEvent(e.getEntity(), e.getEntity().getKiller()));
    }
}
