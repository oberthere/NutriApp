package edu.rit.swen262.goal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.rit.swen262.user.User;

public class MaintainWeightTest { 
    private User user;
    private double currentWeight;
    private double targetWeight;
    
    @BeforeEach
    void setUp() {
        // Initialize with realistic test values
        currentWeight = 200.0;
        targetWeight = 200.0;
        
        // Create user with initial physical stats
        user = new User("TestUser", "Password", 72.0, currentWeight, new Date()); 

        user.startNewDay(currentWeight, targetWeight, false);
    }

    @Test
    void testCalculateTargetCalories() {
        user.getGoalComponent().getCurrentGoal().calculateTargetCalories(user);
        assertEquals(targetWeight * 15, user.getGoalComponent().getTargetCalories());
    }

    @Test
    void testChangeGoal() {
        user.startNewDay(currentWeight, targetWeight - 5, false);
        assertTrue(user.getGoalComponent().getCurrentGoal() instanceof LoseWeight);

        user.startNewDay(currentWeight, targetWeight + 5, false);
        assertTrue(user.getGoalComponent().getCurrentGoal() instanceof GainWeight);
    }

    @Test
    void testPhysicalFitnessImpact() {
        user.startNewDay(currentWeight, targetWeight, true);
        user.getGoalComponent().getCurrentGoal().calculateTargetCalories(user);
        assertEquals((targetWeight * 15) + 400, user.getGoalComponent().getTargetCalories());
    }

    @Test
    void testToString() {
        assertEquals("Maintain Weight", user.getGoalComponent().getCurrentGoal().toString());
    }
}
