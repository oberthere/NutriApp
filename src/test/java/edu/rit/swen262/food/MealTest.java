package edu.rit.swen262.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.csv.csvReader;

public class MealTest {
    private static Ingredient butter;
    private static Ingredient cheese;
    private static Ingredient apple;
    private static Recipe breakfastRecipe;
    private static Recipe dessertRecipe;

    @BeforeAll
    static void setup() {
        // Load real ingredients from CSV
        new csvReader().ingredientReader();
        
        // Get actual ingredients from pantry
        butter = PantryStock.getIngredientByName("butter");
        cheese = PantryStock.getIngredientByName("cheese");
        apple = PantryStock.getIngredientByName("apple");
        
        // Create realistic recipes using pantry ingredients
        breakfastRecipe = new Recipe("Breakfast", 
            List.of(butter, cheese), 
            "Melt butter, add cheese");
            
        dessertRecipe = new Recipe("Dessert", 
            List.of(apple, butter), 
            "Slice apples, drizzle with butter");
    }

    @Test
    void testMealCreation() {
        Meal testMeal = new Meal("Test Meal", 
            List.of(breakfastRecipe), 
            MealType.BREAKFAST);
            
        assertEquals("Test Meal", testMeal.getName());
        assertEquals(MealType.BREAKFAST, testMeal.getMealType());
        assertEquals(1, testMeal.getRecipes().size());
        assertEquals(breakfastRecipe, testMeal.getRecipes().get(0));
    }

    @Test
    void testMealNutritionCalculations() {
        Meal comboMeal = new Meal("Combo Meal", 
            List.of(breakfastRecipe, dessertRecipe), 
            MealType.LUNCH);
            
        // Verify calculations sum all recipe values
        assertEquals(breakfastRecipe.getCalories() + dessertRecipe.getCalories(), 
                    comboMeal.getCalories());
        assertEquals(breakfastRecipe.getFat() + dessertRecipe.getFat(), 
                    comboMeal.getFat(), 0.001);
        assertEquals(breakfastRecipe.getProtein() + dessertRecipe.getProtein(), 
                    comboMeal.getProtein(), 0.001);
        assertEquals(breakfastRecipe.getCarbs() + dessertRecipe.getCarbs(), 
                    comboMeal.getCarbs(), 0.001);
        assertEquals(breakfastRecipe.getFiber() + dessertRecipe.getFiber(), 
                    comboMeal.getFiber(), 0.001);
    }

    @Test
    void testSingleRecipeMeal() {
        Meal simpleMeal = new Meal("Simple Meal", 
            List.of(breakfastRecipe), 
            MealType.DINNER);
            
        // Verify values match the single recipe
        assertEquals(breakfastRecipe.getCalories(), simpleMeal.getCalories());
        assertEquals(breakfastRecipe.getFat(), simpleMeal.getFat(), 0.001);
        assertEquals(breakfastRecipe.getProtein(), simpleMeal.getProtein(), 0.001);
    }

    @Test
    void testMealTypeBehavior() {
        Meal breakfast = new Meal("Morning", List.of(breakfastRecipe), MealType.BREAKFAST);
        Meal lunch = new Meal("Noon", List.of(breakfastRecipe), MealType.LUNCH);
        Meal dinner = new Meal("Evening", List.of(breakfastRecipe), MealType.DINNER);
        
        assertNotEquals(breakfast.getMealType(), lunch.getMealType());
        assertNotEquals(lunch.getMealType(), dinner.getMealType());
    }

    @Test
    void testEmptyMeal() {
        // While commands prevent this, the class should handle it gracefully
        Meal emptyMeal = new Meal("Empty", new ArrayList<>(), MealType.LUNCH);
        
        assertEquals(0, emptyMeal.getCalories());
        assertEquals(0.0, emptyMeal.getFat(), 0.001);
        assertEquals(0.0, emptyMeal.getProtein(), 0.001);
        assertEquals(0.0, emptyMeal.getCarbs(), 0.001);
        assertEquals(0.0, emptyMeal.getFiber(), 0.001);
    }

    @Test
    void testZeroCalorieMeal() {
        Recipe zeroRecipe = new Recipe("Zero", List.of(), "Empty");
        Meal meal = new Meal("Zero Meal", List.of(zeroRecipe), MealType.BREAKFAST);
        assertEquals(0, meal.getCalories());
    }
}