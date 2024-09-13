package me.keegan.chameleon_rpg.game.managers.list.damage;

import lombok.Getter;
import me.keegan.chameleon_rpg.ChameleonRPG;
import me.keegan.chameleon_rpg.game.managers.ChameleonManager;
import me.keegan.chameleon_rpg.utils.events.model.types.LivingEntityDamageByLivingEntityCEvent;
import me.keegan.chameleon_rpg.utils.objects.classes.singleton.StaticInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;

public final class DamageManager extends ChameleonManager {
    private static final HashMap<LivingEntityDamageByLivingEntityCEvent, LivingEntityDamageProfile> damageProfiles = new HashMap<>();

    @Getter
    @StaticInstance
    private static DamageManager instance;

    private DamageManager() {}

    public double calculateDamage(LivingEntityDamageByLivingEntityCEvent e) {
        /*
         * Damage is calculated additively.
         * How damage works:
         *
         * Subtract the reduction damage from the additive damage
         * For example, -100.0% reduction + 50.0% additive = -50.0% enchant damage
         * Then, multiply the *absolute* enchant damage by the final damage
         * And subtract that number by the final damage if its negative,
         * If positive, add it to the final damage.
         */

        /*
         * Final damage is 0 if there is any absorption,
         * however absorption is still calculated after all effects and armor just like final damage.
         *
         * So absorption is the final damage but negative if there is any present
         * if absorption is not present, it will be 0
         */

        LivingEntityDamageProfile damageProfile = this.getDamageProfile(e);

        double finalDamage = e.getFinalDamage();
        finalDamage += Math.abs(e.getDamage(EntityDamageEvent.DamageModifier.ABSORPTION));

        if (damageProfile.getDamage() == 0.0
                && damageProfile.getReducedDamage() == 0.0
                && damageProfile.getMaxDamage() == Double.POSITIVE_INFINITY) { return finalDamage; }

        double enchantAdditivePercent
                = (damageProfile.getReducedDamage() > damageProfile.getDamage())
                ? -((damageProfile.getReducedDamage() - damageProfile.getDamage()) / 100)
                : (damageProfile.getDamage() - damageProfile.getReducedDamage()) / 100;
        double enchantAdditive = enchantAdditivePercent * finalDamage; // -0.5% * 5 = -2.5 reduction | 0.5% * 5 = 2.5 damage
        double newFinalDamage = Math.max(0.0, enchantAdditive + finalDamage);

        // return the new final damage clamped with the max damage
        return Math.max(0.0, Math.min(damageProfile.getMaxDamage(), newFinalDamage));
    }

    public LivingEntityDamageProfile getDamageProfile(LivingEntityDamageByLivingEntityCEvent e) {
        damageProfiles.putIfAbsent(e, new LivingEntityDamageProfile(e.getVictim()));
        return damageProfiles.get(e);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLivingEntityDamageByLivingEntity(LivingEntityDamageByLivingEntityCEvent e) {
        double damage = this.calculateDamage(e);
        ChameleonRPG.info(damage + " damage");
        ChameleonRPG.info(e.getFinalDamage() + " final damage");
    }
}