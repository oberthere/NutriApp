package edu.rit.swen262.food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PantryStock {
    private static final Map<Ingredient, Integer> ingredientRecord = new HashMap<>();
    private static final Map<Integer, Ingredient> ingredientByID = new HashMap<>();
    private static Map<String, Recipe> recipeRecord = new HashMap<>();
    
    public static void updateIngredients(Ingredient ingredient, int amount) {
        PantryStock.ingredientRecord.put(ingredient, amount);
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
        return PantryStock.ingredientByID.get(ID);
    }

    public static int getIngredientCountByID(int ID) {
        Ingredient ingredient = PantryStock.ingredientByID.get(ID);
        return PantryStock.ingredientRecord.get(ingredient);
    }

    public static void addRecipe(Recipe recipe) {PantryStock.recipeRecord.put(recipe.getName(), recipe);}
    public static Map<String, Recipe> getRecipeRecord() {return Collections.unmodifiableMap(PantryStock.recipeRecord);}

}
