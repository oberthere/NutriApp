package edu.shoppingList.strag.strategy;

import java.util.List;

import edu.rit.swen262.food.Food;
import edu.shoppingList.ShoppingList;

public interface ShopCriteriaStrategy {
    static int lowStockQuantityValue = 5;
    ShoppingList buildShoppingList(Food food);
}
