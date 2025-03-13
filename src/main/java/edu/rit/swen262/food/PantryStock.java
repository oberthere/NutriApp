package edu.rit.swen262.food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PantryStock {
    private static final Map<Ingredient, Integer> ingredientRecord = new HashMap<>();
    private static List<Recipe> recipeRecord = new ArrayList<>();
    
    public static void updateIngredients(Ingredient ingredient, int amount) {
        PantryStock.ingredientRecord.put(ingredient, amount);
    }

    public static void updateIngredientRecord(Map<Ingredient, Integer> ingredientRecord) {
        PantryStock.ingredientRecord.clear();
        for (Ingredient ingredient : ingredientRecord.keySet()) {
            PantryStock.ingredientRecord.put(ingredient, ingredientRecord.get(ingredient));
        }
    }

    public static Map<Ingredient, Integer> getAllIngredients() {
        return Collections.unmodifiableMap(PantryStock.ingredientRecord);
    }

}
