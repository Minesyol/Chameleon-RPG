package me.keegan.chameleon_rpg.game.managers.list.damage;

import lombok.Getter;
import org.bukkit.entity.LivingEntity;

/**
 * This class contains information about the custom damage
 * from the LivingEntityDamageEvent
 * <p>
 * Calculations must be done in another class to provide
 * as much flexibility in calculations as possible and to
 * avoid confusion that you cannot make your own calculations.
 */
@Getter
public final class LivingEntityDamageProfile {
    private double damage, reducedDamage, multipliedDamage, trueDamage;
    private double maxDamage;

    private final LivingEntity victim;

    {
        maxDamage = Double.POSITIVE_INFINITY;
    }

    public LivingEntityDamageProfile(LivingEntity victim) {
        this.victim = victim;
    }

    public LivingEntityDamageProfile addDamage(double damage) {
        this.damage += damage;
        return this;
    }

    public LivingEntityDamageProfile reduceDamage(double reducedDamage) {
        this.reducedDamage = reducedDamage;
        return this;
    }

    public LivingEntityDamageProfile addMultipliedDamage(double multipliedDamage) {
        this.multipliedDamage += multipliedDamage;
        return this;
    }

    public LivingEntityDamageProfile addTrueDamage(double trueDamage) {
        this.trueDamage += trueDamage;
        return this;
    }

    public LivingEntityDamageProfile setMaxDamage(double maxDamage) {
        this.maxDamage = maxDamage;
        return this;
    }
}