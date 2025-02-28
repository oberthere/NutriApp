package edu.rit.swen262.history;

import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.workout.Workout;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.workout.HighIntensityStrategy;
import edu.rit.swen262.workout.LowIntensityStrategy;
import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.Recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PersonalHistoryTest {
    private PersonalHistory personalHistory;
    private DailyHistoryService dh1;
    private DailyHistoryService dh2;

    @BeforeEach
    void setUp() {
        personalHistory = new PersonalHistory();

        // pancake ingredients
        List<Ingredient> pancakeIngredients = Arrays.asList(
            new Ingredient("Flour", 100, 5, 1.0, 3.0, 20.0),
            new Ingredient("Milk", 50, 3, 2.0, 3.5, 5.0),
            new Ingredient("Egg", 70, 3, 5.0, 6.0, 1.0)
        );

        // recipe for a pancake
        Recipe pancake = new Recipe("Pancakes", pancakeIngredients, 
            "Combine the flour, eggs, milk and salt in a large bowl and whisk to combine. Whisk it until it's smooth and there are no lumps.\r\n" + 
            "Pour the batter into small rounds onto a hot, lightly greased skillet and flip after about 20 to 30 seconds. Let cook for an additional 15 to 20 seconds.\r\n" +
            "Serve hot with maple syrup on top. Enjoy!");

        // omelette ingredients
        List<Ingredient> omeletteIngredients = Arrays.asList(
            new Ingredient("Egg", 70, 70, 5.0, 6.0, 1.0),
            new Ingredient("Cheese", 80, 135, 6.0, 5.0, 0.0)
        );

        // recipe for an omelette
        Recipe omelette = new Recipe("Omelette", omeletteIngredients, 
            "Beat the eggs until smooth. Pour eggs into a pan and wait until the edges crisp. " +
            "Then, put the cheese in the center. Fold one side of the egg over the other. Enjoy!");

        // breakfast combo meal
        List<Recipe> recipes = Arrays.asList(pancake, omelette);
        Meal meal = new Meal("Breakfast Combo", recipes);

        // workouts
        IntensityStrategy highIntensity = new HighIntensityStrategy();
        IntensityStrategy lowIntensity = new LowIntensityStrategy();
        Workout w1 = new Workout(30, highIntensity);
        Workout w2 = new Workout(60, lowIntensity);

        // DailyHistoryService objects for testing
        dh1 = new DailyHistoryService(new Date(), 180.5, 2000, Arrays.asList(meal), Arrays.asList(w1));
        dh2 = new DailyHistoryService(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000), 175.0, 1800, Arrays.asList(meal), Arrays.asList(w2));
    
        personalHistory.addDailyHistory(dh1);
        personalHistory.addDailyHistory(dh2);
    }

    @Test
    void testGetDailyHistoryInfo() {
        List<DailyHistoryService> history = personalHistory.getHistory();
        assertEquals(2, history.size());
    }

    @Test
    void testAddDailyHistory() {
        assertEquals(2, personalHistory.getHistory().size());
    }

    @Test
    void testGetWeightTrend() {
        Map<Date, Double> weightTrend = personalHistory.getWeightTrend();
        assertEquals(2, weightTrend.size());
    }

    @Test
    void testGetCalorieTrend() {
        Map<Date, Integer> calorieTrend = personalHistory.getCalorieTrend();
        assertEquals(2, calorieTrend.size());
    }

    @Test
    void testGetWorkoutTrend() {
        Map<Date, List<Workout>> workoutTrend = personalHistory.getWorkoutTrend();
        assertEquals(2, workoutTrend.size());
    }

    @Test
    void testGetMealTrend() {
        Map<Date, List<Meal>> mealTrend = personalHistory.getMealTrend();
        assertEquals(2, mealTrend.size());
    }
}


