package edu.rit.swen262.user.service;

import edu.rit.swen262.food.Food;
import edu.shoppingList.ShoppingList;
import edu.shoppingList.strag.strategy.ShopCriteriaStrategy;

public class ShoppingListService {
    private ShopCriteriaStrategy shopCriteria;

    public void setCriteria(ShopCriteriaStrategy shopCriteria) {
        this.shopCriteria = shopCriteria;
    }

    public ShoppingList generateShoppingList(Food food) {
        return this.shopCriteria.buildShoppingList(food);
        //Only the ShopCriteriaLowRecipeStrategy requires a food input which is meant to be the recipe
        //For other strategy, the paramter input doesn't get used.
    }
}
