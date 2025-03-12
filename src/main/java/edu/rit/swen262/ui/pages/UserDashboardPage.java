package edu.rit.swen262.ui.pages;

import edu.rit.swen262.food.Meal;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.CreateUserMealCommand;
import edu.rit.swen262.ui.commands.GoCommand;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;
import edu.rit.swen262.workout.Workout;

import java.util.ArrayList;
import java.util.List;

public class UserDashboardPage extends Page {
    public UserDashboardPage(PageData pageData) {
        super(pageData); // Call the parent constructor
        this.pageName = "User Dashboard"; // Assign page name
        this.userCommands = new ArrayList<>();
        this.userCommands.add(new CreateUserMealCommand(pageData));
        this.userCommands.add(new GoCommand(pageData.getPageRunner()));
    }

    @Override
    public void printContent() {
        super.printContent();

        User currentUser = pageData.getCurrentUser();
        if (currentUser == null) {
            System.out.println("Error: No user is selected.");
            return;
        }

        System.out.println("User Dashboard Page");
        System.out.println("User: " + currentUser.getName());

        System.out.println("\nGeneral Info:");
        System.out.println("\tHeight: " + currentUser.getHeight());
        System.out.println("\tWeight: " + currentUser.getWeight());
        System.out.println("\tBirthdate: " + currentUser.getBirthdate());

        System.out.println("\nGoal Info:");

        GoalService goalService = currentUser.getGoalService(); // Fetch goal service
        if (goalService == null) {
            System.out.println("\tCurrent Goal: No goal set.");
        } else {
            System.out.println("\tCurrent Goal: " + goalService.getCurrentGoal());
            System.out.println("\tTarget Calories: " + goalService.getTargetCalories());
            System.out.println("\tTarget Weight: " + goalService.getTargetWeight());
        }

        System.out.println("\nActivity Info For Today:");

        // Retrieve DailyHistoryService to avoid NullPointerException
        DailyHistoryService dh = currentUser.getDailyHistoryService();
        if (dh == null) {
            System.out.println("\tNo history entry found for today.");
            return;
        }

        System.out.println("\tNet Calories Consumed: " + dh.getNetCalories());
        System.out.println("\tMeals Prepared:");
        printMeal(dh.getPreparedMeals());
        System.out.println("\tMeals Consumed:");
        printMeal(dh.getEatenMeals());
        System.out.println("\tWorkouts Recorded:");
        printWorkouts(dh.getWorkouts());
        System.out.println();
    }

    private void printMeal(List<Meal> meals) {
        if (meals == null || meals.isEmpty()) {
            System.out.println("\t\t- No meals recorded.");
            return;
        }
        for (Meal meal : meals) {
            System.out.println("\t\t- " + meal.getName());
        }
    }

    private void printWorkouts(List<Workout> workouts) {
        if (workouts == null || workouts.isEmpty()) {
            System.out.println("\t\t- No workouts recorded.");
            return;
        }
        for (Workout workout : workouts) {
            System.out.println("\t\t- " + workout.getName());
        }
    }
}
