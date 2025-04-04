package edu.rit.swen262.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.csv.csvReader;

public class PantryStockTest {
    private static Ingredient butter;
    private static Ingredient cheese;
    
    @BeforeAll
    static void setupPantry() {
        new csvReader().ingredientReader();
        butter = PantryStock.getIngredientByName("butter");
        cheese = PantryStock.getIngredientByName("cheese");
    }

    @Test
    void testGetAllIngredients() {
        Map<Ingredient, Integer> ingredients = PantryStock.getAllIngredients();
        assertFalse(ingredients.isEmpty());
        assertTrue(ingredients.size() > 10);
    }

    @Test
    void testGetIngredientIDMap() {
        Map<Integer, Ingredient> idMap = PantryStock.getIngredientIDMap();
        assertEquals(PantryStock.getAllIngredients().size(), idMap.size());
        
        // Verify all IDs map to correct ingredients
        idMap.forEach((id, ingredient) -> {
            assertEquals(id, ingredient.getID());
        });
    }

    @Test
    void testGetIngredientByID() {
        // Get first ingredient's ID from the pantry
        int testId = PantryStock.getAllIngredients().keySet().iterator().next().getID();
        
        Ingredient byId = PantryStock.getIngredientByID(testId);
        assertNotNull(byId);
        assertEquals(testId, byId.getID());
        
        // Test non-existent ID
        assertNull(PantryStock.getIngredientByID(-999));
    }

    @Test
    void testGetIngredientCountByID() {
        // Get first ingredient's ID from the pantry
        int testId = PantryStock.getAllIngredients().keySet().iterator().next().getID();
        
        int count = PantryStock.getIngredientCountByID(testId);
        assertTrue(count > 0);
        
        // Test non-existent ID
        assertEquals(0, PantryStock.getIngredientCountByID(-999));
    }

    @Test
    void testGetIngredientByName() {
        // Test existing ingredient (case-sensitive)
        Ingredient butter = PantryStock.getIngredientByName("butter");
        assertNotNull(butter);
        assertEquals("butter", butter.getName());
        
        // Test case sensitivity
        assertNull(PantryStock.getIngredientByName("BUTTER"));
        
        // Test non-existent ingredient
        assertNull(PantryStock.getIngredientByName("nonexistent_ingredient"));
    }

    @Test
    void testGetIngredientCountByName() {
        // Test existing ingredient
        int butterCount = PantryStock.getIngredientCountByName("butter");
        assertTrue(butterCount > 0);
        
        // Test case sensitivity
        assertEquals(0, PantryStock.getIngredientCountByName("BUTTER"));
        
        // Test non-existent ingredient
        assertEquals(0, PantryStock.getIngredientCountByName("nonexistent_ingredient"));
    }

    @Test
    void testUpdateIngredients() {
        // Get an existing ingredient
        Ingredient butter = PantryStock.getIngredientByName("butter");
        int originalCount = PantryStock.getIngredientCountByName("butter");
        
        // Test adding to count
        PantryStock.updateIngredients(butter, 5);
        assertEquals(originalCount + 5, PantryStock.getIngredientCountByName("butter"));
        
        // Test subtracting from count
        PantryStock.updateIngredients(butter, -3);
        assertEquals(originalCount + 2, PantryStock.getIngredientCountByName("butter"));
        
        // Reset to original count
        PantryStock.updateIngredients(butter, originalCount - (originalCount + 2));
    }

    @Test
    void testAddAndGetRecipes() {
        // Create test ingredients list
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(butter);
        ingredients.add(cheese);
        
        // Create test recipe
        Recipe testRecipe = new Recipe("Test Recipe", ingredients, "Mix ingredients");
        
        // Test adding recipe
        PantryStock.addRecipe(testRecipe);
        
        // Verify retrieval by ID
        assertEquals(testRecipe, PantryStock.getRecipeIDMap().get(testRecipe.getID()));
        
        // Verify retrieval by name
        assertEquals(testRecipe, PantryStock.getRecipeRecord().get("Test Recipe"));
        
        // Verify nutritional calculations
        assertEquals(butter.getCalories() + cheese.getCalories(), 
                    PantryStock.getRecipeRecord().get("Test Recipe").getCalories());
    }

    @Test
    void testRecipeNutritionalValues() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(butter);
        ingredients.add(cheese);
        
        Recipe testRecipe = new Recipe("Nutrition Test", ingredients, "Combine all");
        PantryStock.addRecipe(testRecipe);
        
        Recipe storedRecipe = PantryStock.getRecipeRecord().get("Nutrition Test");
        
        // Test all nutritional calculations
        assertEquals(butter.getCalories() + cheese.getCalories(), storedRecipe.getCalories());
        assertEquals(butter.getFat() + cheese.getFat(), storedRecipe.getFat(), 0.001);
        assertEquals(butter.getProtein() + cheese.getProtein(), storedRecipe.getProtein(), 0.001);
        assertEquals(butter.getCarbs() + cheese.getCarbs(), storedRecipe.getCarbs(), 0.001);
        assertEquals(butter.getFiber() + cheese.getFiber(), storedRecipe.getFiber(), 0.001);
    }

    @Test
    void testMultipleRecipes() {
        int initialCount = PantryStock.getRecipeIDMap().size();

        // first recipe
        List<Ingredient> recipe1Ingredients = new ArrayList<>();
        recipe1Ingredients.add(butter);
        Recipe recipe1 = new Recipe("Recipe 1", recipe1Ingredients, "Instructions 1");
        PantryStock.addRecipe(recipe1);
        
        // second recipe
        List<Ingredient> recipe2Ingredients = new ArrayList<>();
        recipe2Ingredients.add(cheese);
        Recipe recipe2 = new Recipe("Recipe 2", recipe2Ingredients, "Instructions 2");
        PantryStock.addRecipe(recipe2);
        
        // Verify both recipes exist
        assertEquals(initialCount + 2, PantryStock.getRecipeIDMap().size());
        assertEquals(initialCount + 2, PantryStock.getRecipeRecord().size());
        
        // Verify correct IDs
        assertEquals(recipe1, PantryStock.getRecipeIDMap().get(recipe1.getID()));
        assertEquals(recipe2, PantryStock.getRecipeIDMap().get(recipe2.getID()));
        
        // Verify names
        assertEquals(recipe1, PantryStock.getRecipeRecord().get("Recipe 1"));
        assertEquals(recipe2, PantryStock.getRecipeRecord().get("Recipe 2"));
    }

    @Test
    void testRecipeWithNoIngredients() {
        Recipe emptyRecipe = new Recipe("Empty Recipe", new ArrayList<>(), "No ingredients needed");
        PantryStock.addRecipe(emptyRecipe);
        
        // Verify all nutritional values are 0
        assertEquals(0, emptyRecipe.getCalories());
        assertEquals(0.0, emptyRecipe.getFat(), 0.001);
        assertEquals(0.0, emptyRecipe.getProtein(), 0.001);
        assertEquals(0.0, emptyRecipe.getCarbs(), 0.001);
        assertEquals(0.0, emptyRecipe.getFiber(), 0.001);
    }

    @Test
    void testIngredientRecordConsistency() {
        // Verify that ingredientRecord and ingredientByID are in sync
        assertEquals(PantryStock.getAllIngredients().size(), PantryStock.getIngredientIDMap().size());
        
        PantryStock.getAllIngredients().keySet().forEach(ingredient -> {
            assertEquals(ingredient, PantryStock.getIngredientByID(ingredient.getID()));
        });
    }
}