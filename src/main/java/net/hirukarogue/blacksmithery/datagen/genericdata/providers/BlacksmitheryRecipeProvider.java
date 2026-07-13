package net.hirukarogue.blacksmithery.datagen.genericdata.providers;

import net.hirukarogue.blacksmithery.datagen.genericdata.GenericDataBuilder;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.ShapedRecipeData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.ShapelessRecipeData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.RecipeResultData;
import net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients.ShapedRecipeIngredientData;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Pair;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryRecipeProvider extends RecipeProvider {
    public BlacksmitheryRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        List<Supplier<Object>> function = GenericDataBuilder.brain.getOrDefault("shapeless_recipe", List.of());
        if (!function.isEmpty()) {
            for (Supplier<Object> supplier : function) {
                if (supplier.get() instanceof ShapelessRecipeData shapelessRecipeData) {
                    if (!shapelessRecipeData.isValidIngredients()) {
                        System.err.println("Invalid number of ingredients for shapeless recipe: " + shapelessRecipeData.ingredients().size() + " (must be between 1 and 9)");
                        continue;
                    }

                    RecipeResultData result = shapelessRecipeData.resultData();

                    ShapelessRecipeBuilder shaplessRecipe = ShapelessRecipeBuilder.shapeless(result.category(), result.result());

                    List<ItemLike> ingredients = shapelessRecipeData.ingredients();
                    for (ItemLike ingredient : ingredients) {
                        shaplessRecipe.requires((ItemLike) ingredient);
                    }

                    shaplessRecipe.unlockedBy("has_" + result.unlocked_by(), has(result.unlocked_by()));
                    shaplessRecipe.save(recipeOutput, result.recipeName());
                } else {
                    System.err.println("Invalid shapeless recipe supplier: " + supplier);
                }
            }
        }

        function = GenericDataBuilder.brain.getOrDefault("2x2_shaped_recipe", List.of());
        if (!function.isEmpty()) {
            for (Supplier<Object> supplier : function) {
                if (supplier.get() instanceof ShapedRecipeData(RecipeResultData result, ShapedRecipeIngredientData ingredientData)) {
                    if (!ingredientData.isValid2x2recipe()) {
                        System.err.println("Invalid 2x2 shaped recipe: " + ingredientData);
                        continue;
                    }
                    ShapedRecipeBuilder shapedRecipe = ShapedRecipeBuilder.shaped(result.category(), result.result());

                    for (String pattern : ingredientData.patterns()) {
                        shapedRecipe.pattern(pattern);
                    }

                    for (Pair<String, ItemLike> itemValue : ingredientData.itemValues()) {
                        shapedRecipe.define(itemValue.getA().charAt(0), itemValue.getB());
                    }

                    shapedRecipe.unlockedBy("has_" + result.unlocked_by(), has(result.unlocked_by()));
                    shapedRecipe.save(recipeOutput, result.recipeName());
                } else {
                    System.err.println("Invalid 2x2 shaped recipe supplier: " + supplier);
                }
            }
        }

        function = GenericDataBuilder.brain.getOrDefault("3x3_shaped_recipe", List.of());
        if (!function.isEmpty()) {
            for (Supplier<Object> supplier : function) {
                if (supplier.get() instanceof ShapedRecipeData(
                        RecipeResultData result, ShapedRecipeIngredientData ingredientData
                )) {
                    if (!ingredientData.isValid3x3recipe()) {
                        System.err.println("Invalid 3x3 shaped recipe: " + ingredientData);
                        continue;
                    }
                    ShapedRecipeBuilder shapedRecipe = ShapedRecipeBuilder.shaped(result.category(), result.result());

                    for (String pattern : ingredientData.patterns()) {
                        shapedRecipe.pattern(pattern);
                    }

                    for (Pair<String, ItemLike> itemValue : ingredientData.itemValues()) {
                        shapedRecipe.define(itemValue.getA().charAt(0), itemValue.getB());
                    }

                    shapedRecipe.unlockedBy("has_" + result.unlocked_by(), has(result.unlocked_by()));
                    shapedRecipe.save(recipeOutput, result.recipeName());
                } else {
                    System.err.println("Invalid 3x3 shaped recipe supplier: " + supplier);
                }
            }
        }
    }
}
