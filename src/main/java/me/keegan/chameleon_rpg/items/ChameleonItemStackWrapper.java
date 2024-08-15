package me.keegan.chameleon_rpg.items;

import me.keegan.chameleon_rpg.utils.namespacedkeys.ChameleonNamespacedKeys;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public final class ChameleonItemStackWrapper {
    private final ItemStack itemStack;
    private final String namespacedValue;

    /**
     *
     * @param itemStack The itemstack
     * @param namespacedValue The namespaced value used to uniquely identity the itemstack in the persistent data container
     */
    public ChameleonItemStackWrapper(ItemStack itemStack, String namespacedValue) {
        this.itemStack = itemStack;
        this.namespacedValue = namespacedValue;
    }

    public ItemStack getItemStack() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(ChameleonNamespacedKeys.CHAMELEON_ITEMSTACK_KEY, PersistentDataType.STRING, namespacedValue);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
