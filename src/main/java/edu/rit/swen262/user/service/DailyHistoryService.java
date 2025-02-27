package edu.rit.swen262.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.food.Meal;
import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.workout.Workout;


public class DailyHistoryService {
    private Date date;
    private double weight;
    private int targetCalories;
    private List<Meal> meals;
    private List<Workout> workouts;
    private int netCalories;

    // add workout to constructor when implemented
    public DailyHistoryService(Date date, double weight, int targetCalories, List<Meal> meals) {
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
        this.meals = meals;
        this.netCalories = 0;
    }

    public DailyHistoryService(Date date, double weight, int targetCalories, List<Meal> meals, List<Workout> workouts) {
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
        this.meals = meals;
        this.workouts = workouts;
        this.netCalories = 0;
    }

    public Date getDate() {return date;}
    public double getWeight() {return weight;}
    public int getTargetCalories() {return targetCalories;}
    public int getNetCalories() {return netCalories;}
    public List<Meal> getMeals() {return meals;}
    public List<Workout> getWorkouts() { return workouts;}

    public void addWorkout(Workout workout) {this.workouts.add(workout);}

    /* 
    * Whenever the addMeal is called, a check will 
    * be done and if the netCalories is above the 
    * targetCalories, the suggestWorkout will be 
    * called and a suggested workout will pop up 
    * on the screen to recommend the user what to 
    * do to burn the excessive calories
    */
    public void addMeal(Meal meal){
        // TODO: implement adding meal to meals list
        List<Ingredient> lowStockIngredients = new ArrayList<>();
        for (Recipe recipe : meal.getRecipes()) {
            for (Ingredient ingre: recipe.getIngredients()) {
                Map<Ingredient, Integer> record = PantryStock.getAllIngredients();
                int stock = record.get(ingre);
                if (stock <= 0) {
                    lowStockIngredients.add(ingre);
                } 
            }
        }

        if (lowStockIngredients.size() > 0) {
            throw new ArithmeticException("Not enough stock for " + lowStockIngredients + " to be prepared");
        }

        netCalories += meal.getCalories();
        if(netCalories > targetCalories){
            // suggestWorkout returns Workout but this method is void in UML... 
            suggestWorkout();
        }
    }

    public Workout suggestWorkout() {
        return null;
    }

}
