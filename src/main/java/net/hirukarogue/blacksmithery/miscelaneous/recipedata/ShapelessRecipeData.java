package net.hirukarogue.blacksmithery.miscelaneous.recipedata;

import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.RecipeResultData;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public record ShapelessRecipeData(RecipeResultData resultData, List<Ingredient> ingredients) {
    public boolean isValidIngredients() {
        return !ingredients.isEmpty() && ingredients.size() <= 9;
    }
}
