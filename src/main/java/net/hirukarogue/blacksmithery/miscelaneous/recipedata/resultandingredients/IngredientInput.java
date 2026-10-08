package net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Pair;

public record IngredientInput(Ingredient ingredient, boolean preserve) {
    public IngredientInput(Ingredient ingredient) {
        this(ingredient, false);
    }
}
