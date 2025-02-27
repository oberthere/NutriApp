package edu.rit.swen262.user.service;

import java.util.Date;
import java.util.List;

import edu.rit.swen262.food.Meal;
// import edu.rit.swen262.Workout;

public class DailyHistoryService {
    private Date date;
    private double weight;
    private int targetCalories;
    private int netCalories;
    private List<Meal> meals;
    // private List<Workout> workouts;

    public DailyHistoryService(Date date, double weight, int targetCalories) {
        this.date = date;
        this.weight = weight;
        this.targetCalories = targetCalories;
    }

    public Date getDate() {return date;}
    public double getWeight() {return weight;}
    public int getTargetCalories() {return targetCalories;}
    public int getNetCalories() {return netCalories;}
    public List<Meal> getMeals() {return meals;}
    // public List<Workout> getWorkouts() { return workouts;}

    // public void addWorkout(Workout workout) {this.workouts.add(workout);}

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

    public int calculateNetCalories() {
        return 0;
    }

    // public Workout suggestWorkout() {
    //     return null;
    // }

}
