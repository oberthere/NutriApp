package edu.rit.swen262.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {
    
    private Meal meal;

    @BeforeEach
    void setUp() {
        // Create mock recipes
        List<Food> pancakeIngredients = Arrays.asList(
            new Ingredient("Flour", 100, 200, 1.0, 3.0, 20.0, 1.0),
            new Ingredient("Milk", 50, 120, 2.0, 3.5, 5.0, 0.0),
            new Ingredient("Egg", 70, 70, 5.0, 6.0, 1.0, 0.0)
        );

        Food pancake = new Recipe("Pancakes", pancakeIngredients, "Combine the flour, eggs, milk and salt in a large bowl and whisk to combine. Whisk it until it's smooth and there are no lumps.\r\n" + //
                        "Pour the batter into small rounds onto a hot, lightly greased skillet and flip after about 20 to 30 seconds. Let cook for an additional 15 to 20 seconds.\r\n" + //
                        "Serve hot with maple syrup on top. Enjoy!");


        List<Food> omeletteIngredients = Arrays.asList(
            new Ingredient("Egg", 70, 70, 5.0, 6.0, 1.0, 0.0),
            new Ingredient("Cheese", 80, 135, 6.0, 5.0, 1.0, 0.0)
        );

        Food omelette = new Recipe("Omelette", omeletteIngredients, "Beat the eggs until smooth. Pour eggs into a pan and wait until the edges crisp. " +
                                                                        "Then, put the cheese in the center. Fold one side of the egg over the other. Enjoy!");


        // Create a meal
        List<Food> recipes = Arrays.asList(pancake, omelette);
        meal = new Meal("Breakfast Combo", recipes);
    }

    @Test
    void testGetName() {
        assertEquals("Breakfast Combo", meal.getName());
    }

    @Test
    void testGetCalories() {
        assertEquals(595, meal.getCalories());
    }

    @Test
    void testGetFat() {
        assertEquals(19.0, meal.getFat(), 0.001);
    }

    @Test
    void testGetProtein() {
        assertEquals(23.5, meal.getProtein(), 0.001);
    }

    @Test
    void testGetCarbs() {
        assertEquals(28.0, meal.getCarbs(), 0.001);
    }

    @Test
    void testGetFiber() {
        assertEquals(1.0, meal.getFiber(), 0.001);
    }

    @Test
    void testEmptyMeal() {
        Meal emptyMeal = new Meal("Empty Meal", Collections.emptyList());
        assertEquals(0, emptyMeal.getCalories());
        assertEquals(0.0, emptyMeal.getFat(), 0.001);
        assertEquals(0.0, emptyMeal.getProtein(), 0.001);
        assertEquals(0.0, emptyMeal.getCarbs(), 0.001);
        assertEquals(0.0, emptyMeal.getFiber(), 0.001);
    }
}

