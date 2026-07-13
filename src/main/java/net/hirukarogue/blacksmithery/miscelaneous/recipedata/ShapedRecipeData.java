package net.hirukarogue.blacksmithery.miscelaneous.recipedata;

import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.RecipeResultData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.ShapedRecipeIngredientData;

public record ShapedRecipeData(RecipeResultData resultData, ShapedRecipeIngredientData ingredientData) {
}
