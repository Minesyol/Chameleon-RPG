package me.keegan.chameleon_rpg.utils.events.model.types;

import lombok.Getter;
import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import me.keegan.chameleon_rpg.utils.events.model.ChameleonEvent;
import me.keegan.chameleon_rpg.utils.game.entity.ChameleonEquipmentHands;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Getter
public class LivingEntityDamageByLivingEntityCEvent extends ChameleonEvent<EntityDamageByEntityEvent> {
    private final ChameleonEquipmentHands attackerHands;
    private final LivingEntity attacker;
    private final LivingEntity victim;

    public LivingEntityDamageByLivingEntityCEvent(EntityDamageByEntityEvent event) {
        super(event);

        attacker = (LivingEntity) event.getDamager();
        victim = (LivingEntity) event.getEntity();
        attackerHands = new ChameleonEquipmentHands(attacker.getEquipment().getItemInMainHand(), attacker.getEquipment().getItemInOffHand());
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof LivingEntity || e.getEntity() instanceof LivingEntity)) { return; }
        ChameleonEventService.callEvent(new LivingEntityDamageByLivingEntityCEvent(e));
    }
}
