package edu.rit.swen262.food;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        // Create mock ingredients
        Ingredient ingredient1 = new Ingredient("Bread", 70, 0.2, 1.0, 14.0, 1.5);
        Ingredient ingredient2 = new Ingredient("American Cheese", 80, 6.0, 5.0, 1.0, 0.0);
        Ingredient ingredient3 = new Ingredient("Mayonnaise", 90, 10.0, 0.0, 0.0, 0.0);

        // Create a list of ingredients
        List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2, ingredient3);

        // Initialize the Recipe object
        recipe = new Recipe("Grilled Cheese Sandwich", ingredients, 
            "1. Spread the mayo on the bread on one side and place the bread mayo-side down on a hot skillet.\r\n" + //
            "2. Top with cheese, then place another slice of bread on top (mayo-side up).\r\n" + //
            "3. Cook until the bottom slice is lightly browned, then flip.\r\n" + //
            "4. Continue cooking until the cheese is melted.");
    }

    @Test
    void testGetName() {
        assertEquals("Grilled Cheese Sandwich", recipe.getName());
    }

    @Test
    void testGetInstructions() {
        assertEquals(
            "1. Spread the mayo on the bread on one side and place the bread mayo-side down on a hot skillet.\r\n" + //
            "2. Top with cheese, then place another slice of bread on top (mayo-side up).\r\n" + //
            "3. Cook until the bottom slice is lightly browned, then flip.\r\n" + //
            "4. Continue cooking until the cheese is melted.", 
        recipe.getInstructions());
    }

    @Test
    void testGetCalories() {
        assertEquals(240, recipe.getCalories());
    }

    @Test
    void testGetFat() {
        assertEquals(16.2, recipe.getFat(), 0.001);
    }

    @Test
    void testGetProtein() {
        assertEquals(6.0, recipe.getProtein(), 0.001);
    }

    @Test
    void testGetCarbs() {
        assertEquals(15.0, recipe.getCarbs(), 0.001);
    }

    @Test
    void testGetFiber() {
        assertEquals(1.5, recipe.getFiber(), 0.001);
    }

    @Test
    void testEmptyRecipe() {
        Recipe emptyRecipe = new Recipe("Empty Dish", Collections.emptyList(), "No instructions");
        assertEquals(0, emptyRecipe.getCalories());
        assertEquals(0.0, emptyRecipe.getFat(), 0.001);
        assertEquals(0.0, emptyRecipe.getProtein(), 0.001);
        assertEquals(0.0, emptyRecipe.getCarbs(), 0.001);
        assertEquals(0.0, emptyRecipe.getFiber(), 0.001);
    }

    @Test
    void testSingleIngredientRecipe() {
        Ingredient singleIngredient = new Ingredient("Apple", 95, 0.3, 0.0, 25.0, 4.4);
        Recipe singleIngredientRecipe = new Recipe("Apple Snack", List.of(singleIngredient), "Just eat it.");
        
        assertEquals(95, singleIngredientRecipe.getCalories());
        assertEquals(0.3, singleIngredientRecipe.getFat(), 0.001);
        assertEquals(0.5, singleIngredientRecipe.getProtein(), 0.001);
        assertEquals(25.0, singleIngredientRecipe.getCarbs(), 0.001);
        assertEquals(4.4, singleIngredientRecipe.getFiber(), 0.001);
    }

    @Test
    void testGetIngredients() {
        Ingredient ingredient = new Ingredient("Apple", 20, 0.2, 0.0, 4.0, 1.5);
        Recipe recipe = new Recipe("Apple Slices", List.of(ingredient), "Slice and serve.");
        
        List<Ingredient> ingredients = recipe.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals("Apple", ingredients.get(0).getName());
    }

    @Test
    void testLargeValues() {
        Ingredient largeIngredient = new Ingredient("Muckbang", Integer.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Recipe largeRecipe = new Recipe("The Whole Ocean", List.of(largeIngredient), "For the brave and fearless.");
        
        assertEquals(Integer.MAX_VALUE, largeRecipe.getCalories());
        assertEquals(Double.MAX_VALUE, largeRecipe.getFat(), 0.001);
        assertEquals(Double.MAX_VALUE, largeRecipe.getProtein(), 0.001);
        assertEquals(Double.MAX_VALUE, largeRecipe.getCarbs(), 0.001);
        assertEquals(Double.MAX_VALUE, largeRecipe.getFiber(), 0.001);
    }
}

