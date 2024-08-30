package me.keegan.chameleon_rpg.game.items.mystics;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;

public final class MysticFactory {
    private static ItemStack createDefaultMystic(Mystic mystic, @Nullable ChatColor chatColor) {
        ItemStack itemStack = new ItemStack(mystic.getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setLore(mystic.getFreshLore(chatColor));
        itemMeta.setDisplayName(mystic.getTierColors(chatColor) + mystic.getFreshPrefix() + " " + mystic.getName(chatColor));

        CustomMystic customMystic = new CustomMystic();

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @NonNull
    public static ItemStack createMystic(@NonNull Mystic mystic) {
        return createDefaultMystic(mystic, null);
    }

    @NonNull
    public static ItemStack createMystic(@NonNull Mystic mystic, @NonNull ChatColor chatColor) {
        return createDefaultMystic(mystic, chatColor);
    }
}
