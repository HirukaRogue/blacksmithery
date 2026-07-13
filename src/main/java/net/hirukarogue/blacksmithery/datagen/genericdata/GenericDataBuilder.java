package net.hirukarogue.blacksmithery.datagen.genericdata;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GenericDataBuilder {
    public static final Map<String, List<Supplier<Object>>> brain = new HashMap<>();

    public void toDatamap(DataMapType<?,?> dataMapType, Object key, Object value) {
        addEntry("to_datamap", () -> new DataMapData(dataMapType, new Pair<>(key, value)));
    }

    public void shapelessRecipe(ItemStack result, RecipeCategory category, Item unlocked_by, String recipeName, List<ItemLike> ingredients) {
        addEntry("shapeless_recipe", () -> new ShapelessRecipeData(new RecipeResultData(category, result, unlocked_by, recipeName), ingredients));
    }

    public void shapedRecipe2x2(ItemStack result, RecipeCategory category, Item unlocked_by, String recipeName, ShapedRecipeIngredientData ingredientData) {
        addEntry("2x2_shaped_recipe", () -> new ShapedRecipeData(new RecipeResultData(category, result, unlocked_by, recipeName), ingredientData));
    }

    public void shapedRecipe3x3(ItemStack result, RecipeCategory category, Item unlocked_by, String recipeName, ShapedRecipeIngredientData ingredientData) {
        addEntry("3x3_shaped_recipe", () -> new ShapedRecipeData(new RecipeResultData(category, result, unlocked_by, recipeName), ingredientData));
    }

    public static void addEntry(String function, Supplier<Object> supplier) {
        brain.computeIfAbsent(function, k -> new java.util.ArrayList<>()).add(supplier);
    }
}
