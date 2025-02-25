package edu.rit.swen262.history;

import java.util.Date;

// import edu.rit.swen262.food.Meal;
// import edu.rit.swen262.workout.Workout;

public class DailyHistory {
    private Date date;
    private double weight;
    private int targetCalories;
    private int netCalories;
    // private List<Meal> meals;
    // private List<Workout> workouts;

    public DailyHistory(Date date, double weight, int targetCalories) {
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
    }

    public Date getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public int getNetCalories() {
        return netCalories;
    }

    // public List<Meal> getMeals() {
    //     return meals;
    // }

    // public List<Workout> getWorkouts() {
    //     return workouts;
    // }

    public int calculateNetCalories() {
        return 0;
    }

    /* 
     * Whenever the addMeal is called, a check will 
     * be done and if the netCalories is above the 
     * targetCalories, the suggestWorkout will be 
     * called and a suggested workout will pop up 
     * on the screen to recommend the user what to 
     * do to burn the excessive calories
     */
    // public void addMeal(Meal meal){
    //     // TODO: implement adding meal to meals list
    //     netCalories += meal.getCalories();
    //     if(netCalories > targetCalories){
    //         // suggestWorkout returns Workout but this method is void in UML... 
    //         suggestWorkout();
    //     }
    // }

    // public void addWorkout(Workout workout) {
    //     // TODO: implement adding workout to workouts list
    // }

    // public Workout suggestWorkout() {
    //     return null;
    // }
}
