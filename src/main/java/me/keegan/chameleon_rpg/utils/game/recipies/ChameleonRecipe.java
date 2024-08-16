package me.keegan.chameleon_rpg.utils.game.recipies;

import me.keegan.chameleon_rpg.ChameleonRPG;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class ChameleonRecipe {
    private final HashMap<Character, ChameleonIngredientWrapper<?>> ingredientMap;
    private final List<Material> ingredients;
    private final ChameleonRecipeShape shape;
    private final ItemStack recipeResult;
    private final NamespacedKey namespacedKey;

    private ChameleonRecipe(ChameleonRecipeShape shape, List<Material> ingredients, HashMap<Character, ChameleonIngredientWrapper<?>> ingredientMap, ItemStack recipeResult, NamespacedKey namespacedKey) {
        this.shape = shape;
        this.ingredients = ingredients;
        this.ingredientMap = ingredientMap;
        this.recipeResult = recipeResult;
        this.namespacedKey = namespacedKey;
    }

    public Recipe getRecipe() {
        if (shape != null) {
            org.bukkit.inventory.ShapedRecipe shapedRecipe = new org.bukkit.inventory.ShapedRecipe(namespacedKey, recipeResult);

            shapedRecipe.shape(Arrays.stream(shape.getRecipeShape()).toArray(String[]::new));
            ingredientMap.forEach((k, v) -> {
                switch (v.ingredient()) {
                    case Material material -> shapedRecipe.setIngredient(k, material);
                    case RecipeChoice.ExactChoice exactChoice -> shapedRecipe.setIngredient(k, exactChoice);
                    default -> throw new IllegalStateException(String.format("Unexpected ingredient: %s", v.ingredient()));
                }
            });

            return shapedRecipe;
        }

        org.bukkit.inventory.ShapelessRecipe shapelessRecipe = new org.bukkit.inventory.ShapelessRecipe(namespacedKey, recipeResult);
        ingredients.forEach(shapelessRecipe::addIngredient);

        return shapelessRecipe;
    }

    public static RecipeNamespaceKey builder() {
        return new Builder();
    }

    public interface RecipeNamespaceKey {
        RecipeType namespaceKey(String key);
    }

    public interface RecipeType {
        ShapedRecipe shaped();
        ShapelessRecipe shapeless();
    }

    public interface ShapedRecipe {
        ShapedRecipe shape(ChameleonRecipeShape shape);
        <T> ShapedRecipe mapIngredient(char ingredientLetter, ChameleonIngredientWrapper<T> chameleonIngredientWrapper);
        RecipeResult next();
    }

    public interface ShapelessRecipe {
        ShapelessRecipe ingredient(Material ingredientMaterial);
        RecipeResult next();
    }

    public interface RecipeResult {
        BuildRecipe result(ItemStack recipeResult);
    }

    public interface BuildRecipe {
        ChameleonRecipe build();
    }

    public static class Builder implements RecipeNamespaceKey, RecipeType, ShapedRecipe, ShapelessRecipe, RecipeResult, BuildRecipe {
        private final HashMap<Character, ChameleonIngredientWrapper<?>> ingredientMap = new HashMap<>();
        private final List<Material> ingredients = new ArrayList<>();
        private ChameleonRecipeShape shape;
        private ItemStack recipeResult;
        private NamespacedKey namespacedKey;

        @Override
        public RecipeType namespaceKey(String key) {
            namespacedKey = new NamespacedKey(ChameleonRPG.getPlugin(), key);
            return this;
        }

        @Override
        public ShapedRecipe shaped() {
            return this;
        }

        @Override
        public ShapelessRecipe shapeless() {
            return this;
        }

        @Override
        public ShapedRecipe shape(ChameleonRecipeShape shape) {
            this.shape = shape;
            return this;
        }

        @Override
        public <T> ShapedRecipe mapIngredient(char ingredientLetter, ChameleonIngredientWrapper<T> chameleonIngredient) {
            ingredientMap.put(ingredientLetter, chameleonIngredient);
            return this;
        }

        @Override
        public ShapelessRecipe ingredient(Material ingredientMaterial) {
            ingredients.add(ingredientMaterial);
            return this;
        }

        @Override
        public RecipeResult next() {
            return this;
        }

        @Override
        public BuildRecipe result(ItemStack recipeResult) {
            this.recipeResult = recipeResult;
            return this;
        }

        @Override
        public ChameleonRecipe build() {
            return new ChameleonRecipe(shape, ingredients, ingredientMap, recipeResult, namespacedKey);
        }
    }
}
