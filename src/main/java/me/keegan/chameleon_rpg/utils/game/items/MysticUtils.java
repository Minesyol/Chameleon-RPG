package me.keegan.chameleon_rpg.utils.game.items;

import me.keegan.chameleon_rpg.game.items.mystics.data.CustomMystic;
import me.keegan.chameleon_rpg.game.items.mystics.enchants.AMysticEnchant;
import me.keegan.chameleon_rpg.game.items.mystics.types.MysticType;
import me.keegan.chameleon_rpg.utils.files.SerializationUtils;
import me.keegan.chameleon_rpg.utils.game.namespacedkeys.ChameleonNamespacedKeys;
import me.keegan.chameleon_rpg.utils.objects.classes.string.RomanNumeral;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.List;

public final class MysticUtils {
    public static void saveMysticData(@NonNull ItemStack mystic, @NonNull CustomMystic customMystic) {
        ItemMeta itemMeta = mystic.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        persistentDataContainer.set(ChameleonNamespacedKeys.MYSTIC_ITEMSTACK_KEY, PersistentDataType.STRING, SerializationUtils.encodeToBase64(customMystic));
        mystic.setItemMeta(itemMeta);
    }

    @Nullable
    public static CustomMystic getMysticData(@NonNull ItemStack mystic) {
        return SerializationUtils.decodeFromBase64(
                ChameleonNamespacedKeys.getNamespacedValue(ChameleonNamespacedKeys.MYSTIC_ITEMSTACK_KEY, PersistentDataType.STRING, mystic.getItemMeta().getPersistentDataContainer()),
                CustomMystic.class);
    }

    @SuppressWarnings("all")
    public static boolean isMystic(@NonNull ItemStack itemStack) {
        return ChameleonNamespacedKeys.getNamespacedValue(ChameleonNamespacedKeys.MYSTIC_ITEMSTACK_KEY, PersistentDataType.STRING, itemStack.getItemMeta().getPersistentDataContainer()) != null;
    }

    @SuppressWarnings("all")
    public static int getEnchantLevel(@NonNull ItemStack itemStack, @NonNull AMysticEnchant mysticEnchant) {
        return getMysticData(itemStack).getMysticEnchants().get(mysticEnchant.getUniqueName());
    }

    @SuppressWarnings("all")
    public static void addEnchant(@NonNull ItemStack itemStack, @NonNull AMysticEnchant mysticEnchant, int level, boolean requireCompatibility) {
        if (Math.clamp(level, 1, mysticEnchant.getMaxLevel()) != level) { return; }
        CustomMystic customMystic = getMysticData(itemStack);

        if ((requireCompatibility && !mysticEnchant.getMysticTypes().contains(MysticType.valueOf(customMystic.getMysticIdentifier().getLeft())))
                || customMystic.getMysticEnchants().containsKey(mysticEnchant.getUniqueName())) { return; }
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = itemMeta.getLore();
        lore.add("");
        lore.add(String.format("%s%s %s",
                mysticEnchant.isRare() ? String.format("%sRARE! ", ChatColor.LIGHT_PURPLE) : "",
                ChatColor.BLUE + mysticEnchant.getName(),
                level != 1 ? RomanNumeral.convertToRoman(level) : ""));
        lore.addAll(mysticEnchant.getDescription(level));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        HashMap<String, Integer> mysticEnchants = customMystic.getMysticEnchants();
        mysticEnchants.put(mysticEnchant.getUniqueName(), level);

        customMystic.setMysticEnchants(mysticEnchants);
        saveMysticData(itemStack, customMystic);
    }

    @SuppressWarnings("all")
    public static void removeEnchant(@NonNull ItemStack itemStack, AMysticEnchant mysticEnchant) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (int i = 0; i < lore.size() - 1; i++) {
            if (!lore.get(i).contains(mysticEnchant.getName()) || !lore.get(i - 1).isEmpty()) { continue; }

            while (!lore.get(i).isEmpty()) {
                lore.remove(i);
            }

            break;
        }

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        CustomMystic customMystic = getMysticData(itemStack);

        HashMap<String, Integer> mysticEnchants = customMystic.getMysticEnchants();
        mysticEnchants.remove(mysticEnchant.getUniqueName());

        customMystic.setMysticEnchants(mysticEnchants);
        saveMysticData(itemStack, customMystic);
    }

    public static String getTierPrefix(@NonNull MysticType mysticType, int tier) {
        // todo
        return "";
    }
}