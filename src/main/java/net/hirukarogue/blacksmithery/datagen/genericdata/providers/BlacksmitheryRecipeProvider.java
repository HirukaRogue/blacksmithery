package net.hirukarogue.blacksmithery.datagen.genericdata.providers;

import com.mojang.logging.LogUtils;
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
import org.slf4j.Logger;
import oshi.util.tuples.Pair;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlacksmitheryRecipeProvider extends RecipeProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final List<Supplier<ShapelessRecipeData>> SHAPELESS_RECIPES = new ArrayList<>();
    public static final List<Supplier<ShapedRecipeData>> SHAPED_RECIPES = new ArrayList<>();

    public BlacksmitheryRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput, HolderLookup.Provider holderLookup) {
        if (!SHAPELESS_RECIPES.isEmpty()) {
            for (Supplier<ShapelessRecipeData> supplier : SHAPELESS_RECIPES) {
                ShapelessRecipeData data = supplier.get();

                if (!data.isValidIngredients()) {
                    LOGGER.error("Invalid number of ingredients for shapeless recipe: {} (must be between 1 and 9)", data.ingredients().size());
                    continue;
                }

                RecipeResultData result = data.resultData();

                ShapelessRecipeBuilder shaplessRecipe = ShapelessRecipeBuilder.shapeless(result.category(), result.result());

                List<ItemLike> ingredients = data.ingredients();
                for (ItemLike ingredient : ingredients) {
                    shaplessRecipe.requires(ingredient);
                }

                shaplessRecipe.unlockedBy("has_" + result.unlocked_by(), has(result.unlocked_by()));
                shaplessRecipe.save(recipeOutput, result.recipeName());
            }
        }

        if (!SHAPED_RECIPES.isEmpty()) {
            for (Supplier<ShapedRecipeData> supplier : SHAPED_RECIPES) {
                RecipeResultData result = supplier.get().resultData();
                ShapedRecipeIngredientData ingredientData = supplier.get().ingredientData();
                if (ingredientData.isValidPattern()) {
                    ShapedRecipeBuilder shapedRecipe = ShapedRecipeBuilder.shaped(result.category(), result.result());

                    for (String pattern : ingredientData.patterns()) {
                        shapedRecipe.pattern(pattern);
                    }

                    for (Pair<String, ItemLike> itemValue : ingredientData.itemValues()) {
                        shapedRecipe.define(itemValue.getA().charAt(0), itemValue.getB());
                    }

                    shapedRecipe.unlockedBy("has_" + result.unlocked_by(), has(result.unlocked_by()));
                    shapedRecipe.save(recipeOutput, result.recipeName());
                    continue;
                }

                LOGGER.error("Invalid Recipe pattern: {}", Arrays.toString(supplier.get().ingredientData().patterns()));
            }
        }

    }
}
