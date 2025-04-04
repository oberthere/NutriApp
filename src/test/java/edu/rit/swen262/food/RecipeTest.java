package edu.rit.swen262.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.csv.csvReader;

class RecipeTest {
private static Ingredient butter;
    private static Ingredient cheese;
    private static Ingredient apple;

    @BeforeAll
    static void setup() {
        // Load real ingredients from CSV
        new csvReader().ingredientReader();
        
        // Get actual ingredients from pantry
        butter = PantryStock.getIngredientByName("butter");
        cheese = PantryStock.getIngredientByName("cheese");
        apple = PantryStock.getIngredientByName("apple");
    }

    @Test
    void testRecipeCreation() {
        Recipe testRecipe = new Recipe("Test Recipe", 
            List.of(butter, cheese), 
            "Test Instructions");
            
        assertEquals("Test Recipe", testRecipe.getName());
        assertEquals(2, testRecipe.getIngredients().size());
        assertEquals("Test Instructions", testRecipe.getInstructions());
    }

    @Test
    void testNutritionalCalculations() {
        Recipe testRecipe = new Recipe("Nutrition Test", 
            List.of(butter, cheese, apple), 
            "Mix all ingredients");
            
        // Verify calculations sum all ingredient values
        assertEquals(butter.getCalories() + cheese.getCalories() + apple.getCalories(), testRecipe.getCalories());
        assertEquals(butter.getFat() + cheese.getFat() + apple.getFat(), testRecipe.getFat(), 0.001);
        assertEquals(butter.getProtein() + cheese.getProtein() + apple.getProtein(), testRecipe.getProtein(), 0.001);
        assertEquals(butter.getCarbs() + cheese.getCarbs() + apple.getCarbs(), testRecipe.getCarbs(), 0.001);
        assertEquals(butter.getFiber() + cheese.getFiber() + apple.getFiber(), testRecipe.getFiber(), 0.001);
    }

    @Test
    void testSingleIngredientRecipe() {
        Recipe simpleRecipe = new Recipe("Simple", 
            List.of(apple), 
            "Just an apple");
            
        // Verify values match the single ingredient
        assertEquals(apple.getCalories(), simpleRecipe.getCalories());
        assertEquals(apple.getFat(), simpleRecipe.getFat(), 0.001);
        assertEquals(apple.getProtein(), simpleRecipe.getProtein(), 0.001);
    }

    @Test
    void testEmptyIngredientRecipe() {
        // While commands prevent this, the class should handle it
        Recipe emptyRecipe = new Recipe("Empty", new ArrayList<>(), "No ingredients");
         
        assertEquals(0, emptyRecipe.getCalories());
        assertEquals(0.0, emptyRecipe.getFat(), 0.001);
        assertEquals(0.0, emptyRecipe.getProtein(), 0.001);
    }

    @Test
    void testFloatingPointPrecision() {
        // Test with ingredients that have small decimal values
        Recipe precisionRecipe = new Recipe("Precision", 
            List.of(butter, apple), 
            "Test precision");
            
        double expectedFat = butter.getFat() + apple.getFat();
        assertEquals(expectedFat, precisionRecipe.getFat(), 0.0000001);
    }
}

