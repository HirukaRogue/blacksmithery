package net.hirukarogue.blacksmithery.datagen.genericdata;

import net.hirukarogue.blacksmithery.datagen.genericdata.providers.BlacksmitheryDataMapProvider;
import net.hirukarogue.blacksmithery.datagen.genericdata.providers.BlacksmitheryRecipeProvider;
import net.hirukarogue.blacksmithery.miscelaneous.*;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.RecipeResultData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.ShapedRecipeData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.ShapedRecipeIngredientData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.ShapelessRecipeData;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import oshi.util.tuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GenericDataBuilder {
    private static final List<Runnable> buildActions = new ArrayList<>();

    public static GenericDataBuilder toDatamap(DataMapType<?,?> dataMapType, Object key, Object value) {
        buildActions.add(() -> BlacksmitheryDataMapProvider.BRAIN.add(() -> new DataMapData(dataMapType, new Pair<>(key, value))));
        return new GenericDataBuilder();
    }

    public static GenericDataBuilder shapelessRecipe(ItemStack result, RecipeCategory category, Item unlocked_by, String recipeName, List<ItemLike> ingredients) {
        buildActions.add(() -> BlacksmitheryRecipeProvider.SHAPELESS_RECIPES.add( () -> new ShapelessRecipeData(new RecipeResultData(category, result, unlocked_by, recipeName), ingredients)));
        return new GenericDataBuilder();
    }

    public static GenericDataBuilder shapedRecipe(ItemStack result, RecipeCategory category, Item unlocked_by, String recipeName, ShapedRecipeIngredientData ingredientData) {
        buildActions.add(() -> BlacksmitheryRecipeProvider.SHAPED_RECIPES.add(() -> new ShapedRecipeData(new RecipeResultData(category, result, unlocked_by, recipeName), ingredientData)));
        return new GenericDataBuilder();
    }

    public static void build() {
        for (Runnable action : buildActions) {
            action.run();
        }

        buildActions.clear();
    }
}
