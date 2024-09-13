package me.keegan.chameleon_rpg.game.items.mystics.enchants;

import lombok.Getter;
import me.keegan.chameleon_rpg.game.items.mystics.types.MysticType;
import me.keegan.chameleon_rpg.utils.events.IChameleonListener;
import me.keegan.chameleon_rpg.utils.game.items.MysticUtils;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.Set;

public abstract class AMysticEnchant implements IChameleonListener {
    public abstract Set<String> getCommandNames();
    public abstract String getUniqueName();
    public abstract String getName();
    public abstract EnchantGroup getEnchantGroup();
    public abstract Set<EnchantType> getEnchantTypes();
    public abstract Set<MysticType> getMysticTypes();
    public abstract List<String> getDescription(int level);
    public abstract int getMaxLevel();
    public abstract boolean isRare();
    public abstract boolean isMysticWellAccessible();
    public abstract void execute(int level, Object[] args);

    public enum EnchantGroup {
        LAME, // T1, T2, OR T3
        RESOURCE, // T1, T2, OR T3
        NORMAL, // T2 OR T3
        RARE // T2 OR T3
    }

    @Getter
    public enum EnchantType {
        SWORD(Set.of(Material.GOLDEN_SWORD)),
        BOW(Set.of(Material.BOW)),
        PANTS(Set.of(Material.LEATHER_LEGGINGS));

        private final Set<Material> materials;

        EnchantType(Set<Material> materials) {
            this.materials = materials;
        }
    }

    /*
     * Event param is passed because we might need to do things with it later
     */
    protected final void attemptEnchantExecution(@NonNull ItemStack itemStack, @NonNull AMysticEnchant mysticEnchant, @NonNull Event e, Object... args) {
        if (!MysticUtils.isMystic(itemStack)) { return; }
        this.execute(MysticUtils.getEnchantLevel(itemStack, mysticEnchant), args);
    }
}