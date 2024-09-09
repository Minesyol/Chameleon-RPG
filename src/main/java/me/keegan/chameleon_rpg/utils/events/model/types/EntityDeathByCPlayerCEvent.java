package me.keegan.chameleon_rpg.utils.events.model.types;

import lombok.Getter;
import me.keegan.chameleon_rpg.utils.events.model.ChameleonEvent;
import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import me.keegan.chameleon_rpg.utils.game.entity.ChameleonEquipmentHands;
import me.keegan.chameleon_rpg.utils.game.entity.player.PlayerUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

/**
 * Fires when a player kills a living entity
 * and that player is a registered chameleon player
 */
@Getter
public class EntityDeathByCPlayerCEvent extends ChameleonEvent<EntityDeathEvent> {
    private final LivingEntity victim;

    private final Player killer;
    private final ChameleonEquipmentHands killerHands;

    public EntityDeathByCPlayerCEvent(@NonNull EntityDeathEvent e) {
        super(e);

        this.victim = e.getEntity();
        this.killer = e.getEntity().getKiller();
        this.killerHands = new ChameleonEquipmentHands(killer.getEquipment().getItemInMainHand(), killer.getEquipment().getItemInOffHand());
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
        if (e.getEntity().getKiller() == null || PlayerUtils.getChameleonPlayerFromFile(e.getEntity().getKiller()) == null) { return; }
        ChameleonEventService.callEvent(new EntityDeathByCPlayerCEvent(e));
    }
}
