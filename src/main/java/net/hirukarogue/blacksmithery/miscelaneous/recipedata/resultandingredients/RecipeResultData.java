package net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients;

import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public record RecipeResultData(RecipeCategory category, ItemStack result, Item unlocked_by, String recipeName) {
}
