package me.keegan.chameleon_rpg.utils.game.recipies;

import org.bukkit.Material;
import org.bukkit.inventory.RecipeChoice;

public record ChameleonIngredientWrapper<T> (T ingredient) {
    public ChameleonIngredientWrapper {
        if (!(ingredient instanceof Material || ingredient instanceof RecipeChoice.ExactChoice))
            throw new IllegalArgumentException("Ingredient must either be of type Material or RecipeChoice.ExactChoice");
    }
}
