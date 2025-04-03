package edu.rit.swen262.food;

import java.io.Serializable;
import java.util.Map;

public class PantryRecord implements Serializable {
  private static final long serialVersionUID = 1L;

  private Map<Ingredient, Integer> ingredients;
  private Map<String, Recipe> recipes;

  public PantryRecord() {
    ingredients = PantryStock.getAllIngredients();
    recipes = PantryStock.getRecipeRecord();
  }

  public void readToPantryStock() {
    PantryStock.updateIngredientRecord(ingredients);
    for (Recipe recipe : recipes.values()) {
      PantryStock.addRecipe(recipe);
    }
  }
}