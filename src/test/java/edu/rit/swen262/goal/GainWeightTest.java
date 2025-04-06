package edu.rit.swen262.goal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import edu.rit.swen262.user.User;

public class GainWeightTest {
    private User user;
    private double currentWeight;
    private double targetWeight;
    
    @BeforeEach
    void setUp() {
        // Initialize with realistic test values
        currentWeight = 200.0;
        targetWeight = 220.0;
        
        // Create user with initial physical stats
        user = new User("TestUser", "Password", 72.0, currentWeight, new Date()); 

        user.startNewDay(currentWeight, targetWeight, false);
    }

    @Test
    void testCalculateTargetCalories() {
        user.getGoalComponent().getCurrentGoal().calculateTargetCalories(user);
        assertEquals((targetWeight * 15) + 750, user.getGoalComponent().getTargetCalories());
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
        assertEquals((targetWeight * 15) + 750 + 400, user.getGoalComponent().getTargetCalories());
    }

    @Test
    void testToString() {
        assertEquals("Gain Weight", user.getGoalComponent().getCurrentGoal().toString());
    }
}
