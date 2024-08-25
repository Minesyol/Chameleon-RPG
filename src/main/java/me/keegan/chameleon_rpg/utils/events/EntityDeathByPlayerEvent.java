package me.keegan.chameleon_rpg.utils.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class EntityDeathByPlayerEvent extends ChameleonEvent {
    private final LivingEntity livingEntity;
    private final Player player;

    public EntityDeathByPlayerEvent(@NonNull LivingEntity livingEntity, @NonNull Player player) {
        this.livingEntity = livingEntity;
        this.player = player;
    }

    public LivingEntity getVictim() {
        return livingEntity;
    }

    public Player getKiller() {
        return player;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) { return; }
        ChameleonEventService.callEvent(new EntityDeathByPlayerEvent(e.getEntity(), e.getEntity().getKiller()));
    }
}
