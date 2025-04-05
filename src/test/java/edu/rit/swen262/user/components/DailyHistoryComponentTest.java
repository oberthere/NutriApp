package edu.rit.swen262.user.components;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.MealType;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.user.components.DailyHistoryComponent;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DailyHistoryComponentTest {
    private DailyHistoryComponent dailyHistoryComponent;

    @BeforeEach
    public void setUp() {
        dailyHistoryComponent = new DailyHistoryComponent("TestUser", new Date(), 70);
    }

    @Test
    public void testGetUserID() {
        assertEquals("TestUser", dailyHistoryComponent.getUserID());
    }

    @Test
    public void testGetDate() {
        assertEquals(new Date(), dailyHistoryComponent.getDate());
    }

    @Test
    public void testGetWeight() {
        assertEquals(70, dailyHistoryComponent.getWeight());
    }

    // next 4 should all be empty
    @Test
    public void testGetPreparedMeals() {
        assertEquals(0, dailyHistoryComponent.getPreparedMeals().size());
    }

    @Test
    public void testGetEatenMeals() {
        assertEquals(0, dailyHistoryComponent.getEatenMeals().size());
    }

    @Test
    public void testGetWorkouts() {
        assertEquals(0, dailyHistoryComponent.getWorkouts().size());
    }

    @Test
    public void testGetNetCalories() {
        assertEquals(0, dailyHistoryComponent.getNetCalories());
    }

    // evil tests
    // @Test
    // public void testPrepareMeal() {
    //     // Test preparing a meal with enough stock
    //     List<Recipe> recipes = new ArrayList<>();
    //     // Add recipes with ingredients in stock
    //     // ...

    //     try {
    //         dailyHistoryService.prepareMeal("Breakfast", recipes, MealType.BREAKFAST);
    //         assertTrue(dailyHistoryService.getPreparedMeals().contains(new Meal("Breakfast", recipes, MealType.BREAKFAST)));
    //     } catch (LowStockException e) {
    //         fail("Unexpected LowStockException");
    //     }

    //     // Test preparing a meal with insufficient stock
    //     List<Recipe> recipes2 = new ArrayList<>();
    //     // Add recipes with ingredients out of stock
    //     // ...

    //     try {
    //         dailyHistoryService.prepareMeal("Lunch", recipes2, MealType.LUNCH);
    //         fail("Expected LowStockException");
    //     } catch (LowStockException e) {
    //         // Expected exception
    //     }
    // }

    // @Test
    // public void testEatMeal() {
    //     // Test eating a prepared meal
    //     Meal preparedMeal = new Meal("Breakfast", new ArrayList<>(), MealType.BREAKFAST);
    //     dailyHistoryService.getPreparedMeals().add(preparedMeal);

    //     try {
    //         dailyHistoryService.eatMeal(preparedMeal);
    //         assertTrue(dailyHistoryService.getEatenMeals().contains(preparedMeal));
    //         assertFalse(dailyHistoryService.getPreparedMeals().contains(preparedMeal));
    //     } catch (NetCaloriesOverflowException e) {
    //         fail("Unexpected NetCaloriesOverflowException");
    //     }

    //     // Test eating a meal that was not prepared
    //     Meal unpreparedMeal = new Meal("Lunch", new ArrayList<>(), MealType.LUNCH);

    //     try {
    //         dailyHistoryService.eatMeal(unpreparedMeal);
    //         fail("Expected exception");
    //     } catch (NetCaloriesOverflowException e) {
    //         // Expected exception
    //     }
    // }

    // @Test
    // public void testSuggestWorkout() {
    //     // Test suggesting a workout
    //     dailyHistoryService.getNetCalories() = 1500; // Excess calories = 500

    //     Workout suggestedWorkout = dailyHistoryService.suggestWorkout();
    //     assertNotNull(suggestedWorkout);
    //     assertEquals("Jogging", suggestedWorkout.getWorkoutName());
    //     assertEquals(50, suggestedWorkout.getDurationMin());
    //     // Verify the suggested workout intensity using PersonalHistory.getWorkoutIntensityTrend()

    //     // Test suggesting a workout when there are no excess calories
    //     dailyHistoryService.getNetCalories() = 2000; // No excess calories

    //     Workout noWorkoutNeeded = dailyHistoryService.suggestWorkout();
    //     assertNull(noWorkoutNeeded);
    // }
}