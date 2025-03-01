package edu.rit.swen262.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.other.exception.LowStockException;
import edu.rit.swen262.other.exception.NetCaloriesOverflowException;
import edu.rit.swen262.workout.Workout;

public class DailyHistoryService {
    private String userID;
    private Date date;
    private double weight;
    private int targetCalories;
    private List<Meal> preparedMeals;
    private List<Meal> eatenMeals;
    private List<Workout> workouts;
    private int netCalories;

    // Constructor for 
    public DailyHistoryService(String userID, Date date, double weight, int targetCalories) {
        this.userID = userID;
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
        this.eatenMeals = new ArrayList<>();
        this.workouts = new ArrayList<>();
        this.netCalories = 0;
    }

    //Constructor for testing purposes
    //TODO Remove once personal history tests is fixed
    public DailyHistoryService(
        Date date, double weight, int targetCalories, 
        List<Meal> preparedMeals, List<Meal> eatenMeals, List<Workout> workouts)
    {
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
        this.preparedMeals = preparedMeals;
        this.eatenMeals = eatenMeals;
        this.workouts = workouts;
        this.netCalories = 0;
    }

    public Date getDate() {return date;}
    public double getWeight() {return weight;}
    public int getTargetCalories() {return targetCalories;}
    public int getNetCalories() {return netCalories;}
    public List<Meal> getPreparedMeals() {return preparedMeals;}
    public List<Meal> getEatenMeals() {return eatenMeals;}
    public List<Workout> getWorkouts() { return workouts;}
    public String getUserID() {return userID;}

    public void addWorkout(Workout workout) {this.workouts.add(workout);}

    /**
     * Puts together a meal with the recipes passed in the parameter.
     * @param mealName
     * @param recipes
     * @return
     * @throws LowStockException if there isn't enough ingredients available 
     */
    public void prepareMeal(String mealName, List<Recipe> recipes) throws LowStockException{
        List<Ingredient> lowStockIngredients = new ArrayList<>();
        for (Recipe recipe : recipes) {
            for (Ingredient ingre: recipe.getIngredients()) {
                Map<Ingredient, Integer> record = PantryStock.getAllIngredients();
                int stock = record.get(ingre);
                if (stock <= 0) {
                    lowStockIngredients.add(ingre);
                } 
            }
        }

        if (lowStockIngredients.size() > 0) {
            throw new LowStockException("Not enough stock for " + lowStockIngredients + " to be prepared");
        }

        this.preparedMeals.add(new Meal(mealName, recipes));
    }
    
    /**
     * An overload for the prepareMeal method that ignores the LowStockException if ignoreException is true. 
     * Meant to be used in testing or ONLY after prepareMeal has raised a LowStockException.
     * @param mealName
     * @param recipes
     * @param ignoreException
     * @return
     */
    public void prepareMeal(String mealName, List<Recipe> recipes, boolean ignoreException){
        try {
            prepareMeal(mealName, recipes);
        } catch (LowStockException e) {
            if (ignoreException) {this.preparedMeals.add(new Meal(mealName, recipes));}
        }
    }
    

    /**
     * Eats the given meal and adds the calories into the daily netCalories count.
     * @param meal
     * @throws NetCaloriesOverflowException if eating the meal will exceed targetCalories
     */
    public void eatMeal(Meal meal) throws NetCaloriesOverflowException {
        
        if (this.netCalories + meal.getCalories() > this.targetCalories)
        {
            throw new NetCaloriesOverflowException("Consuming " + meal.getName() + " will exceed targetCalories");
        }
        this.preparedMeals.remove(meal);
        this.eatenMeals.add(meal);
        this.netCalories += meal.getCalories();
    }

    /**
     * An overload for the eatMeal method that ignores the NetCaloriesOverflowException if ignoreException is true. 
     * Meant to be used in testing or ONLY after eatMeal has raised a NetCaloriesOverflowException.
     * @param meal
     * @param ignoreException
     */
    public void eatMeal(Meal meal, boolean ignoreException) {
        try {
            eatMeal(meal);
        } catch (NetCaloriesOverflowException e) {
            if (ignoreException) {
                this.netCalories += meal.getCalories();
                this.preparedMeals.remove(meal);
                this.eatenMeals.add(meal);
            }
        }
    }

    /**
     * This gets would be called on by the UI after meal is added if the netCal are exceeded
     * @return
     */
    public Workout suggestWorkout() {
        //TODO Suggest a workout based on personal history and current calories
        throw new UnsupportedOperationException("Method not implemented");
    }

}
