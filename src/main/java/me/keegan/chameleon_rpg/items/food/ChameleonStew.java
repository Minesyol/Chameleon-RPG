package me.keegan.chameleon_rpg.items.food;

import me.keegan.chameleon_rpg.items.ChameleonItemStackWrapper;
import me.keegan.chameleon_rpg.items.IChameleonItem;
import me.keegan.chameleon_rpg.utils.namespacedkeys.ChameleonNamespacedKeys;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonIngredient;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonRecipe;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonRecipeShape;
import me.keegan.chameleon_rpg.utils.game.recipies.IChameleonRecipe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public final class ChameleonStew implements IChameleonRecipe, IChameleonItem, Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        ItemStack itemConsumed = e.getItem();

        // we can cast null safely without throwing an exception
        String value = (String) ChameleonNamespacedKeys.getNamespacedValue(ChameleonNamespacedKeys.CHAMELEON_ITEMSTACK_KEY,
                PersistentDataType.STRING,
                itemConsumed.getItemMeta().getPersistentDataContainer());
        if (value == null || !value.equals(getNamespacedValue())) { return; }


    }

    @Override
    public String getNamespacedValue() {
        return "chameleon_stew";
    }

    @Override
    public ChameleonItemStackWrapper createItemStackWrapper() {
        ItemStack itemStack = new ItemStack(Material.SUSPICIOUS_STEW);

        SuspiciousStewMeta itemMeta = (SuspiciousStewMeta) itemStack.getItemMeta();
        itemMeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60 * 20, 3), true);
        itemMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60 * 20, 14), true);
        itemMeta.addCustomEffect(new PotionEffect(PotionEffectType.DARKNESS, 60 * 20, 4), true);

        List<String> lore = new ArrayList<>();
        lore.add(String.format("%sBecome a chameleon", ChatColor.GRAY));

        itemMeta.setDisplayName(String.format("%sChameleon Stew", ChatColor.WHITE));
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return new ChameleonItemStackWrapper(itemStack, getNamespacedValue());
    }

    @Override
    public ChameleonRecipe[] getChameleonRecipes() {
        return new ChameleonRecipe[]{
                ChameleonRecipe.builder()
                        .namespaceKey("Chameleon_stew")
                        .shaped()
                        .shape(new ChameleonRecipeShape("LLL", "LSL", "LLL"))
                        .mapIngredient('L', new ChameleonIngredient<>(Material.LIME_DYE))
                        .mapIngredient('S', new ChameleonIngredient<>(Material.MUSHROOM_STEW))
                        .next()
                        .result(createItemStackWrapper().getItemStack())
                        .build()};
    }
}
