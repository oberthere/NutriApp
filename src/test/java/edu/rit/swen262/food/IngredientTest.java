package edu.rit.swen262.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.csv.csvReader;

public class IngredientTest {
    private static final PrintStream originalOut = System.out;
    private static ByteArrayOutputStream testOut;

    @BeforeAll
    static void setupPantry() {
        // Added this to remove the csv reader print statement when testing
        // Redirect System.out to capture/suppress output
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        
        // Load ingredients (output will be captured)
        new csvReader().ingredientReader();
        
        // Restore original output stream
        System.setOut(originalOut);
    }

    @Test
    void testCommonIngredientsExist() {
        // Test case-insensitive lookup since CSV parser converts to lowercase
        assertNotNull(PantryStock.getIngredientByName("butter"));
        assertNotNull(PantryStock.getIngredientByName("cheese"));
        assertNotNull(PantryStock.getIngredientByName("apple"));
    }

    @Test
    void testIngredientNutritionalValues() {
        Ingredient butter = PantryStock.getIngredientByName("butter");
        assertNotNull(butter);
        assertEquals(717, butter.getCalories());
        assertEquals(81.11, butter.getFat(), 0.001);
        assertEquals(0.85, butter.getProtein(), 0.001);
    }

    @Test
    void testIngredientIDMapping() {
        // Get first ingredient from pantry
        Ingredient firstIngredient = PantryStock.getAllIngredients().keySet().iterator().next();
        int id = firstIngredient.getID();
        
        // Test ID lookup
        assertEquals(firstIngredient, PantryStock.getIngredientByID(id));
        assertTrue(PantryStock.getIngredientCountByID(id) > 0);
    }

    @Test
    void testNameProcessing() {
        // Check the parser's name processing logic
        assertNotNull(PantryStock.getIngredientByName("butter"));
        assertNull(PantryStock.getIngredientByName("BUTTER,WITH SALT"));
    }

    @Test
    void testNoDuplicateNames() {
        long uniqueNameCount = PantryStock.getAllIngredients().keySet().stream()
            .map(Ingredient::getName)
            .distinct()
            .count();
        
        assertEquals(PantryStock.getAllIngredients().size(), uniqueNameCount);
    }

    @Test
    void testIngredientEqualityAndHashCode() {
        Ingredient butter1 = PantryStock.getIngredientByName("butter");
        Ingredient butter2 = PantryStock.getIngredientByID(butter1.getID());
        
        assertEquals(butter1, butter2);
        assertEquals(butter1.hashCode(), butter2.hashCode());
    }

    @Test
    void testToStringFormat() {
        Ingredient butter = PantryStock.getIngredientByName("butter");
        
        // Actual output: [#0] butter [Calories: 717, Fat: 81.11g, Protein: 0.85g, Carbs: 0.0g, Fiber: 0.06g]
        String expectedPattern = "\\[#\\d+\\] butter \\[Calories: 717, Fat: 81\\.11g, Protein: 0\\.85g, Carbs: 0\\.0g, Fiber: 0\\.06g\\]";
        assertTrue(butter.toString().matches(expectedPattern));
    }

    @Test
    void testGetIngredientCaseSensitivity() {
        // PantryStock lookup is case-sensitive
        Ingredient lower = PantryStock.getIngredientByName("butter");
        Ingredient upper = PantryStock.getIngredientByName("BUTTER");
        assertNotNull(lower);
        assertNull(upper);
    }

    @Test
    void testAllIngredientsHavePositiveCounts() {
        PantryStock.getAllIngredients().values().forEach(count -> {
            assertTrue(count > 0);
        });
    }

    @Test
    void testIngredientIDUniqueness() {
        Set<Integer> ids = new HashSet<>();
        for (Ingredient ingredient : PantryStock.getAllIngredients().keySet()) {
            assertFalse(ids.contains(ingredient.getID()), 
                "Duplicate ID found: " + ingredient.getID());
            ids.add(ingredient.getID());
        }
    }
}