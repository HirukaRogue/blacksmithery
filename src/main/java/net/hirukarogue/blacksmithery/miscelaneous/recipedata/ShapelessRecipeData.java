package net.hirukarogue.blacksmithery.miscelaneous.recipedata;

import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.RecipeResultData;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public record ShapelessRecipeData(RecipeResultData resultData, List<ItemLike> ingredients) {
    public boolean isValidIngredients() {
        return !ingredients.isEmpty() && ingredients.size() <= 9;
    }
}
