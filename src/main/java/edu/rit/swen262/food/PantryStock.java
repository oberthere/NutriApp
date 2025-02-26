package edu.rit.swen262.food;

import java.util.Map;

public class PantryStock {
    private Map<Ingredient, Integer> ingredients;

    public PantryStock(Map<Ingredient, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public void updateIngredients(Ingredient ingredient, int amount) {this.ingredients.put(ingredient, amount);}
}
