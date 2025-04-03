package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.GoCommand;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.GoalComponent;
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
        System.out.println("\tHeight: " + currentUser.getHeight() + " inches");
        System.out.println("\tWeight: " + currentUser.getWeight() + " pounds");
        System.out.println("\tBirthdate: " + currentUser.getBirthdate());

        System.out.println("\nGoal Info:");

        GoalComponent goalComponent = currentUser.getGoalComponent(); // Fetch goal service
        if (goalComponent == null) {
            System.out.println("\tCurrent Goal: No goal set.");
        } else {
            System.out.println("\tCurrent Goal: " + goalComponent.getCurrentGoal());
            System.out.println("\tTarget Calories: " + goalComponent.getTargetCalories());
            System.out.println("\tTarget Weight: " + goalComponent.getTargetWeight() + " pounds");
        }

        System.out.println();
    }
}