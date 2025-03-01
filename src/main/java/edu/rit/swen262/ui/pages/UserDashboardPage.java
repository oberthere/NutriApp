package edu.rit.swen262.ui.pages;

import java.util.List;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;
import edu.rit.swen262.workout.Workout;

public class UserDashboardPage extends Page {
    private PageData pageData;

    public UserDashboardPage(PageData pageData) {
        super.pageName = "User Dashboard";
        this.pageData = pageData;
    }

    @Override
    public void printContent() {
        super.printContent();

        User user = pageData.getCurrentUser();
        GoalService goal = user.getGoalService();
        DailyHistoryService dh = user.getDailyHistoryService();


        System.out.println("User: " + user.getName());
        System.out.println();
        System.out.println("General Info:");
        System.out.println("Height: " + user.getHeight());
        System.out.println("Weight: " + user.getWeight());
        System.out.println("Birthdate: " + user.getBirthdate());
        
        System.out.println();
        System.out.println("Goal Info:");
        System.out.println("Current Goal: " + goal.getCurrentGoal().getClass());
        System.out.println("Target Calories: " + goal.getTargetCalories());
        System.out.println("Target Weight: " + goal.getTargetWeight());
        
        System.out.println();
        System.out.println("Activity Info For Today:");
        System.out.println("Net Calories Consumed: " + dh.getNetCalories());
        System.out.println("Meals Consumed:");
        printMealConsumed(dh.getEatenMeals());
        System.out.println("Workouts Recorded:");
        printWorkouts(dh.getWorkouts());
        
    }   

    private void printMealConsumed(List<Meal> meals) {
        for (Meal meal : meals) {
            System.out.println("\t- " + meal.getName());
        }
    }

    private void printWorkouts(List<Workout> workouts) {
        for (Workout workout : workouts) {
            System.out.println("\t- " + workout.getName());
        }
    }
}
