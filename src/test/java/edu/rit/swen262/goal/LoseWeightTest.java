package edu.rit.swen262.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.user.User;

public class LoseWeightTest {
    private User user;
    private double currentWeight;
    private double targetWeight;
    
    @BeforeEach
    void setUp() {
        // Initialize with realistic test values
        currentWeight = 200.0;
        targetWeight = 180.0;
        
        // Create user with initial physical stats
        user = new User("TestUser", "Password", 72.0, currentWeight, new Date()); 

        user.startNewDay(currentWeight, targetWeight, false);
    }

    @Test
    void testCalculateTargetCalories() {
        user.getGoalComponent().getCurrentGoal().calculateTargetCalories(user);
        assertEquals((targetWeight * 15) - 750, user.getGoalComponent().getTargetCalories());
    }

    @Test
    void testChangeGoal() {
        user.startNewDay(currentWeight, currentWeight, false);
        assertTrue(user.getGoalComponent().getCurrentGoal() instanceof MaintainWeight);
    }

    @Test
    void testPhysicalFitnessImpact() {
        user.startNewDay(currentWeight, targetWeight, true);
        user.getGoalComponent().getCurrentGoal().calculateTargetCalories(user);
        assertEquals((targetWeight * 15) - 750 + 400, user.getGoalComponent().getTargetCalories());
    }

    @Test
    void testToString() {
        assertEquals("Lose Weight", user.getGoalComponent().getCurrentGoal().toString());
    }

    @AfterAll
    public static void cleanUpTestData() {
        String filename = System.getProperty("nutriapp.savefile", "SaveData");
        File file = new File("src/main/resources/data/" + filename);
        if (file.exists() && file.delete()) {
            System.out.println("Test save file '" + filename + "' deleted after test.");
        } else {
            System.out.println("Could not delete test save file '" + filename + "'");
        }
    }
}
