package me.keegan.chameleon_rpg.utils.namespacedkeys;

import me.keegan.chameleon_rpg.ChameleonRPG;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;

public final class ChameleonNamespacedKeys {
    public static final NamespacedKey CHAMELEON_ITEMSTACK_KEY = new NamespacedKey(ChameleonRPG.getPlugin(), "item_identifier");

    @Nullable
    public static <P, C> Object getNamespacedValue(NamespacedKey namespacedKey, PersistentDataType<P, C> persistentDataType, PersistentDataContainer persistentDataContainer) {
        return persistentDataContainer.get(namespacedKey, persistentDataType);
    }
}
