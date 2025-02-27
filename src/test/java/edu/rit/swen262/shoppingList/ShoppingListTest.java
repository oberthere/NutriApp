package edu.rit.swen262.shoppingList;
import edu.rit.swen262.food.Ingredient;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {
    private ShoppingList shoppingList;

    @BeforeEach
    void setUp() {
        List<Ingredient> pancakeIngredients = Arrays.asList(
            new Ingredient("Flour", 100, 5, 1.0, 3.0, 20.0),
            new Ingredient("Milk", 50, 3, 2.0, 3.5, 5.0),
            new Ingredient("Egg", 70, 3, 5.0, 6.0, 1.0)
        );
        shoppingList = new ShoppingList(pancakeIngredients);
    }

    @Test
    void testGetShoppingList() {
        List<Ingredient> expectedIngredients = Arrays.asList(
            new Ingredient("Flour", 100, 5, 1.0, 3.0, 20.0),
            new Ingredient("Milk", 50, 3, 2.0, 3.5, 5.0),
            new Ingredient("Egg", 70, 3, 5.0, 6.0, 1.0)
        );
        assertEquals(expectedIngredients, shoppingList.getShoppingList());
    }

    @Test
    void testRemoveFromShoppingList() {
        shoppingList.removeFromShoppingList(new Ingredient("Flour", 100, 5, 1.0, 3.0, 20.0));
        List<Ingredient> expectedIngredients = Arrays.asList(
            new Ingredient("Milk", 50, 3, 2.0, 3.5, 5.0),
            new Ingredient("Egg", 70, 3, 5.0, 6.0, 1.0)
        );
        assertEquals(expectedIngredients, shoppingList.getShoppingList());
    }

    @Test
    void testAddToShoppingList() {
        shoppingList.addToShoppingList(new Ingredient("Butter", 150, 0.8, 4.5, 15.0, 0.5));
        List<Ingredient> expectedIngredients = Arrays.asList(
            new Ingredient("Flour", 100, 5, 1.0, 3.0, 20.0),
            new Ingredient("Milk", 50, 3, 2.0, 3.5, 5.0),
            new Ingredient("Egg", 70, 3, 5.0, 6.0, 1.0),
            new Ingredient("Butter", 150, 0.8, 4.5, 15.0, 0.5)
        );
        assertEquals(expectedIngredients, shoppingList.getShoppingList());
    }
}

