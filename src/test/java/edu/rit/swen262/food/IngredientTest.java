package edu.rit.swen262.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IngredientTest {
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient(1, "Apple", 52, 0.2, 0.3, 12.0, 2.4);
        ingredient2 = new Ingredient(2, "Apple", 52, 0.2, 0.3, 12.0, 2.4);
    }

    @Test
    void testEquals() {
        assertTrue(ingredient1.equals(ingredient2));
        assertFalse(ingredient1.equals(new Object()));
    }

    @Test
    void testGetCalories() {
        assertEquals(52, ingredient1.getCalories());
    }

    @Test
    void testGetCarbs() {
        assertEquals(12.0, ingredient1.getCarbs(), 0.001);
    }

    @Test
    void testGetFat() {
        assertEquals(0.2, ingredient1.getFat(), 0.001);
    }

    @Test
    void testGetFiber() {
        assertEquals(2.4, ingredient1.getFiber(), 0.001);
    }

    @Test
    void testGetID() {
        assertEquals(1, ingredient1.getID());
    }

    @Test
    void testGetName() {
        assertEquals("Apple", ingredient1.getName());
    }

    @Test
    void testGetProtein() {
        assertEquals(0.3, ingredient1.getProtein(), 0.001);
    }

    @Test
    void testHashCode() {
        assertEquals(ingredient1.getName().hashCode(), ingredient1.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "[#1] Apple [Calories: 52, Fat: 0.2g, Protein: 0.3g, Carbs: 12.0g, Fiber: 2.4g]";
        assertEquals(expectedString, ingredient1.toString());
    }
}