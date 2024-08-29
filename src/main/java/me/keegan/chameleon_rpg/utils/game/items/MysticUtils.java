package me.keegan.chameleon_rpg.utils.game.items;

import me.keegan.chameleon_rpg.game.items.mystics.CustomMystic;
import me.keegan.chameleon_rpg.utils.files.SerializationUtils;
import me.keegan.chameleon_rpg.utils.game.namespacedkeys.ChameleonNamespacedKeys;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class MysticUtils {
    public static void saveMysticData(@NonNull ItemStack mystic, @NonNull CustomMystic customMystic) {
        String encodedCustomMystic = SerializationUtils.encodeToBase64(customMystic);

        PersistentDataContainer persistentDataContainer = mystic.getItemMeta().getPersistentDataContainer();
        persistentDataContainer.set(ChameleonNamespacedKeys.MYSTIC_ITEMSTACK_KEY, PersistentDataType.STRING, encodedCustomMystic);
    }

    @Nullable
    public static CustomMystic getMysticData(@NonNull ItemStack mystic) {
        return SerializationUtils.decodeFromBase64(
                ChameleonNamespacedKeys.getNamespacedValue(ChameleonNamespacedKeys.MYSTIC_ITEMSTACK_KEY, PersistentDataType.STRING, mystic.getItemMeta().getPersistentDataContainer()),
                CustomMystic.class);
    }

}
