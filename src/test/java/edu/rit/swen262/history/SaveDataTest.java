package edu.rit.swen262.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import edu.rit.swen262.csv.csvReader;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.DailyHistoryComponent;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaveDataTest {
    String testFile;

    @SuppressWarnings("unchecked")
    @BeforeAll
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        testFile = "TestSaveData_" + UUID.randomUUID();
        System.setProperty("nutriapp.savefile", testFile);
        SaveData.deserializeFromSave(); // Start clean
        
        // Clear userDataRecord
        Field userDataField = SaveData.class.getDeclaredField("userDataRecord");
        userDataField.setAccessible(true);
        Map<String, UserData> userMap = (Map<String, UserData>) userDataField.get(null);
        userMap.clear();

        // Clear teamDataRecord
        Field teamDataField = SaveData.class.getDeclaredField("teamDataRecord");
        teamDataField.setAccessible(true);
        Map<String, TeamData> teamMap = (Map<String, TeamData>) teamDataField.get(null);
        teamMap.clear();
        
        // Reflectively access the private static 'history' field in SaveData
        Field historyField = SaveData.class.getDeclaredField("history");
        historyField.setAccessible(true);
        Map<String, List<DailyHistoryComponent>> historyMap = 
            (Map<String, List<DailyHistoryComponent>>) historyField.get(null);
        historyMap.clear();
        
        PantryStock.updateIngredientRecord(new HashMap<>());
        new ArrayList<>(PantryStock.getRecipeRecord().keySet()).forEach(name ->
            PantryStock.removeRecipe(PantryStock.getRecipeRecord().get(name))
        );
    }

    @Test
    void testUserDataIsSavedAndRestored() {
        User user = new User("TestUser", "Pass", 180.0, 70.0, new Date());
        SaveData.addUserData(user.createUserData());

        SaveData.serializeHistoryToSave();
        SaveData.deserializeFromSave();

        assertTrue(SaveData.getUserData().containsKey("TestUser"));
        assertEquals("Pass", SaveData.getUserData().get("TestUser").getPassword());
    }

    @Test
    void testTeamDataIsSavedAndRestored() {
        User user = new User("TestUser", "Pass", 180.0, 70.0, new Date());
        Team team = new Team("Dev Champs");
        team.acceptMember(user);
        SaveData.addTeam(team);

        SaveData.serializeHistoryToSave();
        SaveData.deserializeFromSave();

        assertTrue(SaveData.getTeamData().containsKey("Dev Champs"));
        assertEquals("Dev Champs", SaveData.getTeamData().get("Dev Champs").getTeamName());
    }

    @Test
    void testDailyHistoryIsSavedAndRestored() {
        // Create a simple history component
        DailyHistoryComponent hist = new DailyHistoryComponent("TestUser", new Date(), 180.0);
        SaveData.addDailyHistory(hist);

        SaveData.serializeHistoryToSave();
        SaveData.deserializeFromSave();

        List<DailyHistoryComponent> restored = SaveData.getDailyHistory("TestUser");
        
        assertNotNull(restored);
        assertEquals(1, restored.size());

        DailyHistoryComponent entry = restored.get(0);
        assertEquals("TestUser", entry.getUserID());
        assertEquals(180.0, entry.getWeight());
        assertNotNull(entry.getDate());
    }

    @Test
    void testPantryIsSavedAndRestored() {
        new csvReader().ingredientReader(); // load ingredients first

        Ingredient butter = PantryStock.getIngredientByName("butter");
        assertNotNull(butter, "butter should exist");

        PantryStock.updateIngredients(butter, 49);

        Recipe recipe = new Recipe("Buttered Toast", List.of(butter), "Melt it.");
        PantryStock.addRecipe(recipe);

        SaveData.serializeHistoryToSave();

        PantryStock.updateIngredientRecord(new HashMap<>());
        new ArrayList<>(PantryStock.getRecipeRecord().keySet()).forEach(name ->
            PantryStock.removeRecipe(PantryStock.getRecipeRecord().get(name))
        );

        SaveData.deserializeFromSave();

        assertEquals(50, PantryStock.getIngredientCountByName("butter"));
        assertTrue(PantryStock.getRecipeRecord().containsKey("Buttered Toast"));
    }

    @AfterAll
    void cleanUpTestData() {
        File file = new File("src/main/resources/data/" + testFile);
        if (file.exists() && file.delete()) {
            System.out.println("Deleted test file: " + testFile);
        } else {
            System.out.println("Could not delete test save file '" + testFile + "'");
        }
    }
}
