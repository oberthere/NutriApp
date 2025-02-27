package edu.rit.swen262.shoppingList;

import edu.rit.swen262.shoppingList.ShoppingList;
import edu.rit.swen262.food.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ShoppingListTest {
    private ShoppingList shoppingList;

    @BeforeEach
    void setUp() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("Flour", 100, 200, 1.0, 3.0, 20.0, 1.0),
                new Ingredient("Milk", 50, 120, 2.0, 3.5, 5.0, 0.0),
                new Ingredient("Eggs", 70, 70, 5.0, 6.0, 1.0, 0.0)
        );
        shoppingList = new ShoppingList(ingredients);
    }

    @Test
    void testGetShoppingList() {
        List<Ingredient> expectedIngredients = Arrays.asList(
                new Ingredient("Flour", 100, 200, 1.0, 3.0, 20.0, 1.0),
                new Ingredient("Milk", 50, 120, 2.0, 3.5, 5.0, 0.0),
                new Ingredient("Eggs", 70, 70, 5.0, 6.0, 1.0, 0.0)
        );
        assertEquals(expectedIngredients, shoppingList.getShoppingList());
    }

    @Test
    void testRemoveFromShoppingList() {
        shoppingList.removeFromShoppingList(new Ingredient("Milk", 50, 120, 2.0, 3.5, 5.0, 0.0));
        List<Ingredient> expectedIngredients = Arrays.asList(
                new Ingredient("Flour", 100, 200, 1.0, 3.0, 20.0, 1.0),
                new Ingredient("Eggs", 70, 70, 5.0, 6.0, 1.0, 0.0)
        );
        assertEquals(expectedIngredients, shoppingList.getShoppingList());
    }

    @Test
    void testAddToShoppingList() {
        shoppingList.addToShoppingList(new Ingredient("Butter", 150, 250, 0.8, 4.5, 15.0, 0.5));
        List<Ingredient> expectedIngredients = Arrays.asList(
                new Ingredient("Flour", 100, 200, 1.0, 3.0, 20.0, 1.0),
                new Ingredient("Milk", 50, 120, 2.0, 3.5, 5.0, 0.0),
                new Ingredient("Eggs", 70, 70, 5.0, 6.0, 1.0, 0.0),
                new Ingredient("Butter", 150, 250, 0.8, 4.5, 15.0, 0.5)
        );
        assertEquals(expectedIngredients, shoppingList.getShoppingList());
    }
}

