package edu.rit.swen262.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.csv.csvReader;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;

public class PantryRecordTest {
    private static final String testFile = "TestSaveData_" + UUID.randomUUID();;
    Ingredient butter;
    Ingredient cheese;
    Recipe grilledCheese;

    @BeforeEach
    void setUp() {
        System.setProperty("nutriapp.savefile", testFile);

        // Load ingredients from CSV
        new csvReader().ingredientReader();

        // Grab ingredients
        butter = PantryStock.getIngredientByName("butter");
        cheese = PantryStock.getIngredientByName("cheese");

        // Set stock
        PantryStock.updateIngredientRecord(Map.of(butter, 42, cheese, 73));

        // Add a recipe
        grilledCheese = new Recipe("Grilled Cheese", List.of(butter, cheese), "Toast it.");
        PantryStock.addRecipe(grilledCheese);
    }

    @Test
    void testPantrySaveLoadWithSpecificIngredientsAndRecipe() {
        // Save to file (TestSaveData from pom config)
        SaveData.serializeHistoryToSave();

        // Clear pantry
        PantryStock.updateIngredientRecord(new HashMap<>());
        for (String name : new ArrayList<>(PantryStock.getRecipeRecord().keySet())) {
            PantryStock.removeRecipe(PantryStock.getRecipeRecord().get(name));
        }

        // Confirm pantry is empty
        assertEquals(0, PantryStock.getAllIngredients().size());
        assertEquals(0, PantryStock.getRecipeRecord().size());

        // Load from file
        SaveData.deserializeFromSave();

        // Assert specific ingredient stock
        assertEquals(42, PantryStock.getIngredientCountByName("butter"));
        assertEquals(73, PantryStock.getIngredientCountByName("cheese"));

        // Assert recipe exists and is correct
        assertTrue(PantryStock.getRecipeRecord().containsKey("Grilled Cheese"));
        Recipe restored = PantryStock.getRecipeRecord().get("Grilled Cheese");
        assertEquals("Toast it.", restored.getInstructions());
        assertEquals(2, restored.getIngredients().size());
    }

    @AfterAll
    public static void cleanUpTestData() {
        File file = new File("src/main/resources/data/" + testFile);
        if (file.exists() && file.delete()) {
            System.out.println("Deleted test file: " + testFile);
        } else {
            System.out.println("Could not delete test save file '" + testFile + "'");
        }
    }
}
