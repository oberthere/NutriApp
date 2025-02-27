package edu.rit.swen262.shoppingList;
import java.util.ArrayList;
import java.util.List;

import edu.rit.swen262.food.Ingredient;
public class ShoppingList {
    private ArrayList<Ingredient> shoppingList;

    public ShoppingList() {
        this.shoppingList = new ArrayList<Ingredient>();
    }
    public ShoppingList(List<Ingredient> shoppingList) {
        this.shoppingList = new ArrayList<>(shoppingList);
    }

    public List<Ingredient> getShoppingList() {return this.shoppingList;}
    public void removeFromShoppingList(Ingredient ingredient) {this.shoppingList.remove(ingredient);}
    public void addToShoppingList(Ingredient ingredient) {this.shoppingList.add(ingredient);}
}

