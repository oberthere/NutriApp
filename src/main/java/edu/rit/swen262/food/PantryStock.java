package edu.rit.swen262.food;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class PantryStock {
    private static final Map<Ingredient, Integer> ingredientRecord = new HashMap<>();
    private static final Map<Integer, Ingredient> ingredientByID = new HashMap<>();
    private static Map<String, Recipe> recipeRecord = new HashMap<>();
    private static Map<Integer, Recipe> recipeByID = new HashMap<>();
    
    public static void updateIngredients(Ingredient ingredient, int amount) {
        PantryStock.ingredientRecord.put(ingredient, amount + ingredientRecord.get(ingredient));
    }

    public static void updateIngredientRecord(Map<Ingredient, Integer> ingredientRecord) {
        PantryStock.ingredientRecord.clear();
        for (Ingredient ingredient : ingredientRecord.keySet()) {
            PantryStock.ingredientRecord.put(ingredient, ingredientRecord.get(ingredient));
            PantryStock.ingredientByID.put(ingredient.getID(), ingredient);
        }
    }

    public static Map<Ingredient, Integer> getAllIngredients() {
        return Collections.unmodifiableMap(PantryStock.ingredientRecord);
    }

    public static Map<Integer, Ingredient> getIngredientIDMap() {
        return Collections.unmodifiableMap(PantryStock.ingredientByID);
    }

    public static Ingredient getIngredientByID(int ID) {
        for (int id : PantryStock.ingredientByID.keySet()) {
            if (id == ID) {
                return PantryStock.ingredientByID.get(ID);
            }
        }
        return null;
    }

    public static int getIngredientCountByID(int ID) {
        for (int id : PantryStock.ingredientByID.keySet()) {
            if (id == ID) {
                return PantryStock.ingredientRecord.get(PantryStock.ingredientByID.get(ID));
            }
        }
        return 0;
    }

    public static Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : PantryStock.ingredientRecord.keySet()) {
            if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public static int getIngredientCountByName(String name) {
        for (Ingredient ingredient : PantryStock.ingredientRecord.keySet()) {
            if (ingredient.getName().equals(name)) {
                return PantryStock.ingredientRecord.get(ingredient);
            }
        }
        return 0;
    }

    public static void addRecipe(Recipe recipe) {
        PantryStock.recipeByID.put(recipe.getID(), recipe);
        PantryStock.recipeRecord.put(recipe.getName(), recipe);
    }

    public static Map<Integer, Recipe> getRecipeIDMap() {
        return Collections.unmodifiableMap(PantryStock.recipeByID);
    }

    public static Map<String, Recipe> getRecipeRecord() {
        return Collections.unmodifiableMap(PantryStock.recipeRecord);
    }
}