package me.keegan.chameleon_rpg.utils.events.model.types;

import lombok.Getter;
import me.keegan.chameleon_rpg.utils.events.model.ChameleonEvent;
import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

@Getter
public final class EntityDeathByPlayerCEvent extends ChameleonEvent<EntityDeathEvent> {
    private final LivingEntity livingEntity;
    private final Player player;

    public EntityDeathByPlayerCEvent(@NonNull EntityDeathEvent e) {
        super(e);

        this.livingEntity = e.getEntity();
        this.player = e.getEntity().getKiller();
    }

    public List<ItemStack> getDrops() {
        return getEvent().getDrops();
    }

    public void setDrops(List<ItemStack> drops) {
        getEvent().getDrops().clear();
        getEvent().getDrops().addAll(drops);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) { return; }
        ChameleonEventService.callEvent(new EntityDeathByPlayerCEvent(e));
    }
}
