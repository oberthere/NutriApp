package edu.rit.swen262.user.components;

import java.io.Serializable;  // Import Serializable
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.MealType;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.other.exception.LowStockException;
import edu.rit.swen262.other.exception.NetCaloriesOverflowException;
import edu.rit.swen262.workout.Workout;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.history.SaveData;

public class DailyHistoryComponent implements Serializable { // Now serializable
    private static final long serialVersionUID = 1L;  // Recommended for Serializable classes

    private String userID;
    private Date date;
    private double weight;
    private int targetCalories;
    private GoalComponent goalComponent;
    private List<Meal> preparedMeals;
    private List<Meal> eatenMeals;
    private List<Workout> workouts;
    private int netCalories;

    public DailyHistoryComponent(String userID, Date date, double weight) {
        this.userID = userID;
        this.date = date;
        this.weight = weight;
        this.preparedMeals = new ArrayList<>();
        this.eatenMeals = new ArrayList<>();
        this.workouts = new ArrayList<>();
        this.netCalories = 0;
    
        // initialize a default GoalComponent 
        this.goalComponent = new GoalComponent(false, weight, weight);
        this.targetCalories = goalComponent.getTargetCalories();
    }
    
    public String getUserID() {return userID;}
    public Date getDate() {return date;}
    public double getWeight() {return weight;}
    public int getTargetCalories() {return targetCalories;}
    public GoalComponent getGoalComponent() {return goalComponent;}
    public List<Meal> getPreparedMeals() {return preparedMeals;}
    public List<Meal> getEatenMeals() {return eatenMeals;}
    public List<Workout> getWorkouts() {return workouts;}
    public int getNetCalories() {return netCalories;}

    public Meal prepareMeal(String mealName, List<Recipe> recipes, MealType mealType) throws LowStockException {
        List<Ingredient> lowStockIngredients = new ArrayList<>();
        Map<Ingredient, Integer> record = PantryStock.getAllIngredients();

        for (Recipe recipe : recipes) {
            for (Ingredient ingre : recipe.getIngredients()) {
                int stock = record.getOrDefault(ingre, 0);
                if (stock <= 0) {
                    lowStockIngredients.add(ingre);
                } else {
                    PantryStock.updateIngredients(ingre, stock - 1); // Deduct ingredient from pantry stock
                }
            }
        }

        if (!lowStockIngredients.isEmpty()) {
            throw new LowStockException("Not enough stock for " + lowStockIngredients + " to be prepared");
        }

        Meal newMeal = new Meal(mealName, recipes, mealType);
        this.preparedMeals.add(newMeal);
        return newMeal;
    }
    
    public void removeMeal(Meal meal) {
      this.preparedMeals.remove(meal);
    }

    public void eatMeal(Meal meal) throws NetCaloriesOverflowException {
        if (!this.preparedMeals.contains(meal)) {
            System.out.println("Error: Meal must be prepared before eating.");
            return;
        }

        if (this.netCalories + meal.getCalories() > this.targetCalories) {
            throw new NetCaloriesOverflowException("Consuming " + meal.getName() + " will exceed target calories.");
        }

        this.preparedMeals.remove(meal);
        this.eatenMeals.add(meal);
        this.netCalories += meal.getCalories();
    }
    
    public void removeEatenMeal(Meal meal) {
        this.eatenMeals.remove(meal);
        this.preparedMeals.add(meal);
        this.netCalories -= meal.getCalories();
    }

    /**
     * Suggests a workout based on excess calories.
     */
    public Workout suggestWorkout() {
        int excessCalories = this.netCalories - this.targetCalories;
        if (excessCalories <= 0) {
            return null; // No workout needed
        }

        IntensityStrategy avgIntensity = SaveData.getWorkoutIntensityTrend(this.userID);

        // Example: Find an appropriate workout to burn excess calories using the average intensity
        for (Workout workout : workouts) {
            double burnedCalories = workout.getIntensity().caloriesBurned(workout);
            if (burnedCalories >= excessCalories && workout.getIntensity().equals(avgIntensity)) {
                return workout;
            }
        }

        // If no existing workout with the average intensity burns enough, suggest a generic one
        Workout genericWorkout = new Workout("Jogging", excessCalories / 10, avgIntensity);
        return genericWorkout;
    }

    public void addWorkout(Workout workout) {
        if (workout == null) {
            System.out.println("Error: Workout cannot be null.");
            return;
        }

        this.workouts.add(workout);
    }
}