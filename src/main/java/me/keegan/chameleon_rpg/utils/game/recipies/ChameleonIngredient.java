package me.keegan.chameleon_rpg.utils.game.recipies;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;

public record ChameleonIngredient<T> (T ingredient) {
    public ChameleonIngredient {
        if (!(ingredient instanceof Material || ingredient instanceof RecipeChoice.ExactChoice))
            throw new IllegalArgumentException("Ingredient must either be of type Material or RecipeChoice.ExactChoice");
    }
}
