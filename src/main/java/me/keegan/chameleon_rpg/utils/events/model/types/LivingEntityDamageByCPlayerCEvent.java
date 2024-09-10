package me.keegan.chameleon_rpg.utils.events.model.types;

import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import me.keegan.chameleon_rpg.utils.game.entity.player.PlayerUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class LivingEntityDamageByCPlayerCEvent extends LivingEntityDamageByLivingEntityCEvent {
    private final LivingEntity victim1;
    private final Player attacker1;

    public LivingEntityDamageByCPlayerCEvent(EntityDamageByEntityEvent e) {
        super(e);

        this.victim1 = super.getVictim();
        this.attacker1 = (Player) super.getAttacker();
    }

    public LivingEntity getVictim() {
        return this.victim1;
    }

    public Player getAttacker() {
        return attacker1;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLivingEntityDamageByLivingEntity(LivingEntityDamageByLivingEntityCEvent e) {
        if (!(e.getAttacker() instanceof Player) || PlayerUtils.getChameleonPlayerFromFile((Player) e.getAttacker()) == null) { return; }
        ChameleonEventService.callEvent(new LivingEntityDamageByCPlayerCEvent(e.getEvent()));
    }
}
