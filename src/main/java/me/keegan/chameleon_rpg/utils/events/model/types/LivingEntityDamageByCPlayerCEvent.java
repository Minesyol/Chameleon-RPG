package me.keegan.chameleon_rpg.utils.events.model.types;

import lombok.Getter;
import me.keegan.chameleon_rpg.utils.events.ChameleonEventService;
import me.keegan.chameleon_rpg.utils.game.entity.player.PlayerUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Getter
public class LivingEntityDamageByCPlayerCEvent extends LivingEntityDamageByLivingEntityCEvent {
    private final LivingEntity victim;
    private final Player attacker;

    public LivingEntityDamageByCPlayerCEvent(EntityDamageByEntityEvent e) {
        super(e);

        this.victim = super.getVictim();
        this.attacker = (Player) super.getAttacker();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLivingEntityDamageByLivingEntity(LivingEntityDamageByLivingEntityCEvent e) {
        if (!(e.getAttacker() instanceof Player) || PlayerUtils.getChameleonPlayerFromFile((Player) e.getAttacker()) == null) { return; }
        ChameleonEventService.callEvent(new LivingEntityDamageByCPlayerCEvent(e.getEvent()));
    }
}
