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
    private List<Meal> preparedMeals;
    private List<Meal> eatenMeals;
    private List<Workout> workouts;
    private int netCalories;

    // Constructor for 
    public DailyHistoryService(Date date, double weight, int targetCalories) {
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
        this.eatenMeals = new ArrayList<>();
        this.workouts = new ArrayList<>();
        this.netCalories = 0;
    }

    //Constructor for testing purposes
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
    public List<Meal> getMeals() {return eatenMeals;}
    public List<Workout> getWorkouts() { return workouts;}

    public void addWorkout(Workout workout) {this.workouts.add(workout);}

    /** 
    * Prepares the given meal. If the there isn't enough ingredients available to prepare the meal, 
    * an ArithmeticException gets thrown 
    */
    public void prepareMeal(Meal meal){
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

        this.netCalories += meal.getCalories();
    }

    public void eatMeal(Meal meal) {

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
