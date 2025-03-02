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
        // Create recipes
        List<Ingredient> pancakeIngredients = Arrays.asList(
            new Ingredient("Flour", 100, 5, 1.0, 3.0, 20.0),
            new Ingredient("Milk", 50, 3, 2.0, 3.5, 5.0),
            new Ingredient("Egg", 70, 3, 5.0, 6.0, 1.0)
        );

        Recipe pancake = new Recipe("Pancakes", pancakeIngredients, 
            "Combine the flour, eggs, milk and salt in a large bowl and whisk to combine. Whisk it until it's smooth and there are no lumps.\r\n" + //
            "Pour the batter into small rounds onto a hot, lightly greased skillet and flip after about 20 to 30 seconds. Let cook for an additional 15 to 20 seconds.\r\n" + //
            "Serve hot with maple syrup on top. Enjoy!");


        List<Ingredient> omeletteIngredients = Arrays.asList(
            new Ingredient("Egg", 70, 7.0, 5.0, 6.0, 1.0),
            new Ingredient("Cheese", 80, 6.0, 5.0, 1.0, 0.0)
        );

        Recipe omelette = new Recipe("Omelette", omeletteIngredients, 
            "Beat the eggs until smooth. Pour eggs into a pan and wait until the edges crisp. " +                                                            
            "Then, put the cheese in the center. Fold one side of the egg over the other. Enjoy!");


        // Create a meal
        List<Recipe> recipes = Arrays.asList(pancake, omelette);
        meal = new Meal("Breakfast Combo", recipes, MealType.BREAKFAST);
    }

    @Test
    void testGetName() {
        assertEquals("Breakfast Combo", meal.getName());
    }

    @Test
    void testGetCalories() {
        assertEquals(370, meal.getCalories());
    }

    @Test
    void testGetFat() {
        assertEquals(24.0, meal.getFat(), 0.001);
    }

    @Test
    void testGetProtein() {
        assertEquals(18.0, meal.getProtein(), 0.001);
    }

    @Test
    void testGetCarbs() {
        assertEquals(19.5, meal.getCarbs(), 0.001);
    }

    @Test
    void testGetFiber() {
        assertEquals(27.0, meal.getFiber(), 0.001);
    }

    @Test
    void testEmptyMeal() {
        Meal emptyMeal = new Meal("Empty Meal", Collections.emptyList(), MealType.BREAKFAST);
        assertEquals(0, emptyMeal.getCalories());
        assertEquals(0.0, emptyMeal.getFat(), 0.001);
        assertEquals(0.0, emptyMeal.getProtein(), 0.001);
        assertEquals(0.0, emptyMeal.getCarbs(), 0.001);
        assertEquals(0.0, emptyMeal.getFiber(), 0.001);
    }
}

