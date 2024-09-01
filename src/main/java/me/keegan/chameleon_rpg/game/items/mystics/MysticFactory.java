package me.keegan.chameleon_rpg.game.items.mystics;

import me.keegan.chameleon_rpg.game.items.mystics.data.CustomMystic;
import me.keegan.chameleon_rpg.game.items.mystics.types.IMystic;
import me.keegan.chameleon_rpg.game.items.mystics.types.Mystic;
import me.keegan.chameleon_rpg.utils.classes.string.Inflector;
import me.keegan.chameleon_rpg.utils.game.items.MysticUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import javax.annotation.Nullable;
import java.util.Random;

public final class MysticFactory {
    private static ItemStack createDefaultMystic(@NonNull Mystic mystic, @Nullable ChatColor chatColor) {
        ItemStack itemStack = new ItemStack(mystic.getMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();

        String mysticName = mystic.getName(chatColor);

        itemMeta.setLore(mystic.getFreshLore(chatColor));
        itemMeta.setDisplayName(mystic.getTierColors(chatColor).getFirst() + mystic.getFreshPrefix() + " " + mysticName);

        CustomMystic customMystic = new CustomMystic();
        customMystic.setMysticIdentifier(new MutablePair<>(mystic.getEnumName(), mysticName));

        String requiredPantColor;

        if (mystic.getRequiredPantColor() == IMystic.MysticRequiredPantColor.RANDOM_FRESH) {
            requiredPantColor = Mystic.DEFAULT.getPantColors().keySet().toArray()[(new Random().nextInt(Mystic.DEFAULT.getPantColors().size() - 1))].toString();
        } else if (mystic.getRequiredPantColor() == IMystic.MysticRequiredPantColor.SAME_COLOR) {
            assert chatColor != null : "ChatColor argument cannot be null when MysticRequiredPantColor is same color";
            requiredPantColor = chatColor.toString();
        } else {
            throw new RuntimeException(Inflector.getInstance().titleCase(mystic.getRequiredPantColor().name()) + " has not been implemented yet");
        }

        if (itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
            leatherArmorMeta.setColor(mystic.getPantColors().get(chatColor));
        }

        customMystic.setMysticRequiredColor(requiredPantColor);
        itemStack.setItemMeta(itemMeta);

        MysticUtils.saveMysticData(itemStack, customMystic);
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
