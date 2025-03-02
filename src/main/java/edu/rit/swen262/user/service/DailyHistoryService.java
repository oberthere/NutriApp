package edu.rit.swen262.user.service;

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

public class DailyHistoryService {
    private String userID;
    private Date date;
    private double weight;
    private int targetCalories;
    private List<Meal> preparedMeals;
    private List<Meal> eatenMeals;
    private List<Workout> workouts;
    private int netCalories;

    

    public String getUserID() {return userID;}
    public Date getDate() {return date;}
    public double getWeight() {return weight;}
    public int getTargetCalories() {return targetCalories;}
    public List<Meal> getPreparedMeals() {return preparedMeals;}
    public List<Meal> getEatenMeals() {return eatenMeals;}
    public List<Workout> getWorkouts() {return workouts;}
    public int getNetCalories() {return netCalories;}

    public DailyHistoryService(String userID, Date date, double weight, int targetCalories) {
        this.userID = userID;
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
        this.preparedMeals = new ArrayList<>();
        this.eatenMeals = new ArrayList<>();
        this.workouts = new ArrayList<>();
        this.netCalories = 0;
    }

    public void prepareMeal(String mealName, List<Recipe> recipes, MealType mealType) throws LowStockException {
        List<Ingredient> lowStockIngredients = new ArrayList<>();
        Map<Ingredient, Integer> record = PantryStock.getAllIngredients();

        for (Recipe recipe : recipes) {
            for (Ingredient ingre : recipe.getIngredients()) {
                int stock = record.getOrDefault(ingre, 0);
                if (stock <= 0) {
                    lowStockIngredients.add(ingre);
                } else {
                    record.put(ingre, stock - 1); // Deduct ingredient from pantry stock
                }
            }
        }

        if (!lowStockIngredients.isEmpty()) {
            throw new LowStockException("Not enough stock for " + lowStockIngredients + " to be prepared");
        }

        Meal newMeal = new Meal(mealName, recipes, mealType);
        this.preparedMeals.add(newMeal);
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

    /**
     * Suggests a workout based on excess calories.
     */
    public Workout suggestWorkout() {
        int excessCalories = this.netCalories - this.targetCalories;
        if (excessCalories <= 0) {
            return null; // No workout needed
        }

        // Example: Find an appropriate workout to burn excess calories
        for (Workout workout : workouts) {
            double burnedCalories = workout.getIntensity().calorieBurnAlgorithm(workout);
            if (burnedCalories >= excessCalories) {
                return workout;
            }
        }

        // If no existing workout burns enough, suggest a generic one
        return new Workout("Jogging", excessCalories / 10, new IntensityStrategy() {
            @Override
            public double calorieBurnAlgorithm(Workout workout) {
                return workout.getDurationMin() * 10; // Example: Burn 10 calories per minute
            }
        });
    }
}
