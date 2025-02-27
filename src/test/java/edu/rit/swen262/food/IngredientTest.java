package edu.rit.swen262.food;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class IngredientTest {
    private Ingredient ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient("Tomato", 20, 0.2, 1.0, 4.0, 1.5);
    }

    @Test
    public void testGetName() {
        assertEquals("Tomato", ingredient.getName());
    }

    @Test
    public void testGetCalories() {
        assertEquals(20, ingredient.getCalories());
    }

    @Test
    public void testGetFat() {
        assertEquals(0.2, ingredient.getFat(), 0.001);
    }

    @Test
    public void testGetProtein() {
        assertEquals(1.0, ingredient.getProtein(), 0.001);
    }

    @Test
    public void testGetCarbs() {
        assertEquals(4.0, ingredient.getCarbs(), 0.001);
    }

    @Test
    public void testGetFiber() {
        assertEquals(1.5, ingredient.getFiber(), 0.001);
    }
}
