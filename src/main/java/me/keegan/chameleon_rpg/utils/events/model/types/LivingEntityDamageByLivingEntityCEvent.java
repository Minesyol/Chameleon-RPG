package me.keegan.chameleon_rpg.utils.events.model.types;

import lombok.Getter;
import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import me.keegan.chameleon_rpg.utils.events.model.ChameleonEvent;
import me.keegan.chameleon_rpg.utils.game.entity.ChameleonEquipmentHands;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

@Getter
public class LivingEntityDamageByLivingEntityCEvent extends ChameleonEvent<EntityDamageByEntityEvent> {
    private final ChameleonEquipmentHands attackerHands;
    private final LivingEntity attacker;
    private final LivingEntity victim;
    private final double finalDamage;

    public LivingEntityDamageByLivingEntityCEvent(EntityDamageByEntityEvent e) {
        super(e);

        this.attacker = (LivingEntity) e.getDamager();
        this.victim = (LivingEntity) e.getEntity();
        this.attackerHands = new ChameleonEquipmentHands(attacker.getEquipment().getItemInMainHand(), attacker.getEquipment().getItemInOffHand());
        this.finalDamage = e.getFinalDamage();
    }

    public double getDamage(EntityDamageEvent.DamageModifier damageModifier) {
        return super.getEvent().getDamage(damageModifier);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        /*
         * Comparing the class is necessary because other classes inherit
         * from this, which can call the event twice.
         */
        if (!(e.getDamager() instanceof LivingEntity
                && e.getEntity() instanceof LivingEntity
                && super.isCalledInSuperClass(LivingEntityDamageByLivingEntityCEvent.class, this.getClass()))) { return; }
        ChameleonEventService.callEvent(new LivingEntityDamageByLivingEntityCEvent(e));
    }
}
