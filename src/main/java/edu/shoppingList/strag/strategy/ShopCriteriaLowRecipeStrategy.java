package edu.shoppingList.strag.strategy;

import java.util.List;
import java.util.Map;

import edu.rit.swen262.food.Food;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.shoppingList.ShoppingList;


/**
 * Builds a shopping list of the low ingredients within the given recipe in the parameter
 */
public class ShopCriteriaLowRecipeStrategy implements ShopCriteriaStrategy {
    @Override
    public ShoppingList buildShoppingList(Food food) {
        
        ShoppingList shoppingList = new ShoppingList();
        Map<Ingredient, Integer> ingredientRecord = PantryStock.getAllIngredients();
        Recipe recipe = (Recipe) food;
        for (Food recipeFood : recipe.getIngredients()) {
            Ingredient recipeIngredient = (Ingredient) recipeFood;
            if (ingredientRecord.get(recipeIngredient) <= ShopCriteriaStrategy.lowStockQuantityValue) {
                shoppingList.addToShoppingList(recipeIngredient);
            }
        }
        return shoppingList;
    }
    
}
