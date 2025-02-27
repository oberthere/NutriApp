package edu.rit.swen262.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PantryStockTest {

    private Ingredient flour;
    private Ingredient milk;
    private Ingredient eggs;

    @BeforeEach
    void setUp() {
        // Create test ingredients
        flour = new Ingredient("Flour", 100, 0, 1.0, 3.0, 20.0, 1.0);
        milk = new Ingredient("Milk", 50, 0, 2.0, 3.5, 5.0, 0.0);
        eggs = new Ingredient("Eggs", 70, 0, 5.0, 6.0, 1.0, 0.0);

        // Reset PantryStock for each test
        PantryStock.updateIngredientRecord(new HashMap<>());
    }

    @Test
    void testUpdateIngredients() {
        PantryStock.updateIngredients(flour, 2);
        assertEquals(2, PantryStock.getAllIngredients().get(flour));
    }

    @Test
    void testUpdateIngredientAmount() {
        PantryStock.updateIngredients(milk, 1);
        assertEquals(1, PantryStock.getAllIngredients().get(milk));

        // Update amount
        PantryStock.updateIngredients(milk, 3);
        assertEquals(3, PantryStock.getAllIngredients().get(milk));
    }

    @Test
    void testUpdateIngredientRecord() {
        Map<Ingredient, Integer> newStock = new HashMap<>();
        newStock.put(flour, 5);
        newStock.put(eggs, 10);

        PantryStock.updateIngredientRecord(newStock);
        assertEquals(2, PantryStock.getAllIngredients().size());
        assertEquals(5, PantryStock.getAllIngredients().get(flour));
        assertEquals(10, PantryStock.getAllIngredients().get(eggs));
    }

    @Test
    void testGetAllIngredients() {
        PantryStock.updateIngredients(flour, 2);
        PantryStock.updateIngredients(milk, 4);

        Map<Ingredient, Integer> ingredients = PantryStock.getAllIngredients();
        assertEquals(2, ingredients.size());
        assertEquals(2, ingredients.get(flour));
        assertEquals(4, ingredients.get(milk));
    }

    @Test
    void testGetAllIngredientsIsUnmodifiable() {
        PantryStock.updateIngredients(eggs, 5);
        Map<Ingredient, Integer> ingredients = PantryStock.getAllIngredients();

        assertThrows(UnsupportedOperationException.class, () -> {
            ingredients.put(flour, 3);
        });
    }
}
