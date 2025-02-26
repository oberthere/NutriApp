package edu.shoppingList.strag.strategy;

import java.util.List;

import edu.rit.swen262.food.Food;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.Recipe;
import edu.shoppingList.ShoppingList;

public class ShopCriteriaLowRecipeStrategy implements ShopCriteriaStrategy {

    @Override
    public ShoppingList buildShoppingList(Food food) {
        ShoppingList shoppingList = new ShoppingList();
        Recipe recipe = (Recipe) food;
        for (Food ingredient : recipe.getIngredients()) {
            
        }

        return shoppingList;
    }
    
}
