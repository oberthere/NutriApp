package edu.rit.swen262.food;

public class IngredientRestock {

  private Ingredient ingredient;
  private int amount;

  public IngredientRestock(Ingredient ingredient, int amount) {
    this.ingredient = ingredient;
    this.amount = amount;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public int getAmount() {
    return amount;
  }
}
