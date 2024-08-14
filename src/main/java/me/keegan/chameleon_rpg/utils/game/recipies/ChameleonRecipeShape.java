package me.keegan.chameleon_rpg.utils.game.recipies;

import java.util.Arrays;

/**
 * The shape of the recipe in a 3x3 grid, where _ are spaces instead of only confusing spaces
 * The shape goes from top to bottom
 */
public final class ChameleonRecipeShape {
    /**
     * 3x3 grid
     */
    private static final int SHAPE_GRID_SIZE = 3;
    private final String[] recipeShape;

    public ChameleonRecipeShape(String topOfShape, String middleOfShape, String bottomOfShape) {
        int shapeGridLengthSanity = getShapeGridLengthSanity(topOfShape, middleOfShape, bottomOfShape);
        if (shapeGridLengthSanity != -1) {
            throw new IllegalArgumentException(String.format("Invalid length for recipe shape; expected %d, got %d", SHAPE_GRID_SIZE, shapeGridLengthSanity));
        }

        if (!isShapeGridSane(topOfShape, middleOfShape, bottomOfShape)) {
            throw new IllegalArgumentException("Recipe shape contains a space instead of an underscore");
        }

        recipeShape = new String[]{topOfShape, middleOfShape, bottomOfShape};
    }

    public String[] getRecipeShape() {
        return Arrays.stream(recipeShape)
                .map(string -> string.replaceAll("_", " "))
                .toArray(String[]::new);
    }

    /**
     *
     * @return -1 if shape length sanity is well, else returns the invalid shape length
     */
    private int getShapeGridLengthSanity(String topOfShape, String middleOfShape, String bottomOfShape) {
        return topOfShape.length() == SHAPE_GRID_SIZE
                    ? middleOfShape.length() == SHAPE_GRID_SIZE
                        ? bottomOfShape.length() == SHAPE_GRID_SIZE ? -1
                        : bottomOfShape.length()
                    : middleOfShape.length()
                : topOfShape.length();
    }

    /**
     *
     * @return true if the 3x3 grid of characters are valid, false if otherwise
     */
    private boolean isShapeGridSane(String topOfShape, String middleOfShape, String bottomOfShape) {
        return !topOfShape.contains(" ") && !middleOfShape.contains(" ") && !bottomOfShape.contains(" ");
    }
}