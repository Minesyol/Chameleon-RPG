package me.keegan.chameleon_rpg.items.food;

import me.keegan.chameleon_rpg.items.IChameleonItem;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonIngredient;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonRecipe;
import me.keegan.chameleon_rpg.utils.game.recipies.ChameleonRecipeShape;
import me.keegan.chameleon_rpg.utils.game.recipies.IChameleonRecipe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ChameleonStew implements IChameleonRecipe, IChameleonItem {
    @Override
    public ItemStack getItemStack() {
        return null;
    }

    @Override
    public ChameleonRecipe[] getChameleonRecipes() {
        return new ChameleonRecipe[]{
                ChameleonRecipe.builder()
                        .namespaceKey("Chameleon Soup")
                        .shaped()
                        .shape(new ChameleonRecipeShape("LLL", "LSL", "LLL"))
                        .mapIngredient('L', new ChameleonIngredient<>(Material.LIME_DYE))
                        .mapIngredient('S', new ChameleonIngredient<>(Material.MUSHROOM_STEW))
                        .next()
                        .result(getItemStack())
                        .build()};
    }
}
