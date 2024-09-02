package me.keegan.chameleon_rpg.game.items.mystics.enchants.sword;

import me.keegan.chameleon_rpg.game.items.mystics.enchants.AMysticEnchant;
import me.keegan.chameleon_rpg.game.items.mystics.types.MysticType;
import me.keegan.chameleon_rpg.utils.events.model.types.LivingEntityDamageByLivingEntityCEvent;
import org.bukkit.event.EventHandler;

import java.util.Set;

public class SharpEnchant extends AMysticEnchant {
    @Override
    public Set<String> getCommandNames() {
        return Set.of("Sharp", "Sharpness");
    }

    @Override
    public String getUniqueName() {
        return "sharp";
    }

    @Override
    public String getName() {
        return "Sharp";
    }

    @Override
    public EnchantGroup getEnchantGroup() {
        return EnchantGroup.LAME;
    }

    @Override
    public Set<EnchantType> getEnchantTypes() {
        return Set.of(EnchantType.SWORD);
    }

    @Override
    public Set<MysticType> getMysticTypes() {
        return Set.of(MysticType.SWORD);
    }

    @Override
    public Set<String> getDescription(int level) {
        return Set.of();
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isRare() {
        return false;
    }

    @Override
    public boolean isMysticWellAccessible() {
        return true;
    }

    @Override
    public void execute(int level, Object[] args) {

    }

    @EventHandler
    public void onLivingEntityDamageByLivingEntity(LivingEntityDamageByLivingEntityCEvent e) {
        if (!e.getAttackerHands().mainHand().hasItemMeta()) { return; }
        attemptEnchantExecution(e.getAttackerHands().mainHand(), this, e);
    }
}
