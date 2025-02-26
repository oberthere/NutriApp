package edu.rit.swen262.food;

import java.util.HashMap;
import java.util.Map;

public final class PantryStock {
    private static final Map<Ingredient, Integer> ingredientRecord = new HashMap<>();
    
    public Map<Ingredient, Integer> getAllIngredients() {return PantryStock.ingredientRecord;}
    public void updateIngredients(Ingredient ingredient, int amount) {
        PantryStock.ingredientRecord.put(ingredient, amount);
    }
    public void updateIngredientRecord(Map<Ingredient, Integer> ingredientRecord) {
        PantryStock.ingredientRecord.clear();
        for (Ingredient ingredient : ingredientRecord.keySet()) {
            PantryStock.ingredientRecord.put(ingredient, ingredientRecord.get(ingredient));
        }
    }
}
