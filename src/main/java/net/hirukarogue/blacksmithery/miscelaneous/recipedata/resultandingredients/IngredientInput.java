package net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients;

import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Pair;

public record IngredientInput(ItemLike item, boolean preserve) {
    public IngredientInput(ItemLike item) {
        this(item, false);
    }
}
