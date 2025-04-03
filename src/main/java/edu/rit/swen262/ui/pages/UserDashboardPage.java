package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.GoCommand;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.GoalComponent;
import java.util.ArrayList;

public class UserDashboardPage extends Page {
    public UserDashboardPage(PageData pageData) {
        super(pageData); // Call the parent constructor
        this.pageName = "User Dashboard"; // Assign page name
        this.userCommands = new ArrayList<>();
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

        GoalComponent goalService = currentUser.getGoalService(); // Fetch goal service
        if (goalService == null) {
            System.out.println("\tCurrent Goal: No goal set.");
        } else {
            System.out.println("\tCurrent Goal: " + goalService.getCurrentGoal());
            System.out.println("\tTarget Calories: " + goalService.getTargetCalories());
            System.out.println("\tTarget Weight: " + goalService.getTargetWeight());
        }

        System.out.println();
    }
}
