package me.keegan.chameleon_rpg.utils.game.items;

import me.keegan.chameleon_rpg.game.items.mystics.data.CustomMystic;
import me.keegan.chameleon_rpg.game.items.mystics.enchants.AMysticEnchant;
import me.keegan.chameleon_rpg.utils.files.SerializationUtils;
import me.keegan.chameleon_rpg.utils.game.namespacedkeys.ChameleonNamespacedKeys;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

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

    public static boolean isMystic(@NonNull ItemStack itemStack) {
        return ChameleonNamespacedKeys.getNamespacedValue(ChameleonNamespacedKeys.MYSTIC_ITEMSTACK_KEY, PersistentDataType.STRING, itemStack.getItemMeta().getPersistentDataContainer()) != null;
    }

    // TODO
    public static int getEnchantLevel(@NonNull ItemStack itemStack, @NonNull AMysticEnchant mysticEnchant) {
        return -1;
    }

    public static void addEnchant(@NonNull ItemStack itemStack) {

    }
}