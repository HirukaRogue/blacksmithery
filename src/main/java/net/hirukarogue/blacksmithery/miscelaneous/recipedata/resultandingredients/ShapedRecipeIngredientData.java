package net.hirukarogue.blacksmithery.miscelaneous.recipedata.resultandingredients;

import net.minecraft.world.level.ItemLike;
import oshi.util.tuples.Pair;

import java.util.List;

public record ShapedRecipeIngredientData(String[] patterns, List<Pair<String, ItemLike>> itemValues) {
    public boolean isValid2x2recipe() {
        if (patterns.length > 2 || patterns.length < 1) {
            return false;
        }
        for (String pattern : patterns) {
            if (pattern.length() > 2 || pattern.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public boolean isValid3x3recipe() {
        if (patterns.length > 3 || patterns.length < 1) {
            return false;
        }
        for (String pattern : patterns) {
            if (pattern.length() > 3 || pattern.isEmpty()) {
                return false;
            }
        }

        return true;
    }
}
