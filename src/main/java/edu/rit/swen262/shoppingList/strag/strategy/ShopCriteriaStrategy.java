package edu.rit.swen262.shoppingList.strag.strategy;

import edu.rit.swen262.food.Food;
import edu.rit.swen262.shoppingList.ShoppingList;

public interface ShopCriteriaStrategy {
    static int lowStockQuantityValue = 5;
    ShoppingList buildShoppingList(Food food);
}