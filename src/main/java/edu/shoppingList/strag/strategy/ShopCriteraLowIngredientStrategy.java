package edu.shoppingList.strag.strategy;

import java.util.Map;

import edu.rit.swen262.food.Food;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import edu.shoppingList.ShoppingList;

/**
 * Builds a shopping list of ALL of the low ingredients
 */
public class ShopCriteraLowIngredientStrategy implements ShopCriteriaStrategy {

    @Override
    public ShoppingList buildShoppingList(Food food) {
        ShoppingList shoppingList = new ShoppingList();
        Map<Ingredient, Integer> ingredientRecord = PantryStock.getAllIngredients();
        for (Ingredient ingredient : ingredientRecord.keySet()) {
            if (ingredientRecord.get(ingredient) <= ShopCriteriaStrategy.lowStockQuantityValue) {
                shoppingList.addToShoppingList(ingredient);
            }
        }
        return shoppingList;
    }
    
}
