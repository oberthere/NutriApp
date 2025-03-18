package edu.rit.swen262.user.service;

import java.util.List;

import edu.rit.swen262.food.Food;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.shoppingList.ShoppingList;
import edu.rit.swen262.shoppingList.strag.strategy.ShopCriteriaStrategy;

public class ShoppingListService {
    private ShopCriteriaStrategy shopCriteria;
    private ShoppingList shoppingList;

    public ShopCriteriaStrategy getCriteriaStrategy() { return this.shopCriteria; }

    public List<Ingredient> getShoppingList() { return this.shoppingList.getShoppingList(); }

    public void setCriteria(ShopCriteriaStrategy shopCriteria) {
        this.shopCriteria = shopCriteria;
    }

    public void generateShoppingList(Food food) {
        shoppingList = this.shopCriteria.buildShoppingList(food);
        //Only the ShopCriteriaLowRecipeStrategy requires a food input which is meant to be the recipe
        //For other strategy, the paramter input doesn't get used.
    }
}
