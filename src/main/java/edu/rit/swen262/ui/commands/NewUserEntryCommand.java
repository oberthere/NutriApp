package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.Date;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryComponent;

public class NewUserEntryCommand extends UserCommand {
    private PageData pageData;
    private PageRunner pageRunner;

    public NewUserEntryCommand(PageRunner pageRunner) {
        super.nameString = "NewUserEntry";
        super.helpString = "NewUserEntry [currentWeight] [targetWeight] [targetCalories] [isPhysicalFitnessGoal]";
        this.pageRunner = pageRunner;
        this.pageData = pageRunner.getPageData();
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length != 5) {
            System.out.println("Error: Invalid number of arguments. Usage: NewUserEntry [currentWeight] [targetWeight] [targetCalories] [isPhysicalFitnessGoal]");
            return;
        }

        User user = pageData.getCurrentUser();
        if (user == null) {
            System.out.println("Error: No user selected.");
            return;
        }

        try {
            // checks that the currentWeight is a positive num
            double currentWeight = Double.parseDouble(commandArgs[1]);
            if (currentWeight <= 0) {
                System.out.println("Error: Current weight must be a positive number.");
                return;
            }

            // checks that the targetWeight is a positive num
            double targetWeight = Double.parseDouble(commandArgs[2]);
            if (targetWeight <= 0) {
                System.out.println("Error: Target weight must be a positive number.");
                return;
            }

            // checks that the targetCalories is a positive integer
            int targetCalories = Integer.parseInt(commandArgs[3]);
            if (targetCalories <= 0) {
                System.out.println("Error: Target calories must be a positive number.");
                return;
            }

            // checks that the isPhysicalFitnessGoal is a boolean and then sets it
            if (!commandArgs[4].equalsIgnoreCase("true") && !commandArgs[4].equalsIgnoreCase("false")) {
                System.out.println("Error: Invalid boolean value for 'isPhysicalFitnessGoal'. Please enter 'true' or 'false'.");
                return;
            }
        
            boolean isPhysicalFitnessGoal = commandArgs[4].equalsIgnoreCase("true");

            // Update user user history
            user.startNewDay(currentWeight, targetWeight, targetCalories, isPhysicalFitnessGoal);

            // Create a new UserHistoryService entry
            DailyHistoryComponent userHistory = new DailyHistoryComponent(user.getName(), new Date(), currentWeight, targetCalories);

            // Ensure user history exists
            if (!SaveData.getHistory().containsKey(user.getName())) {
                SaveData.getHistory().put(user.getName(), new ArrayList<>());
            }

            // Add the new user history entry
            SaveData.getHistory().get(user.getName()).add(userHistory);

            // Save the updated history to file
            SaveData.serializeHistoryToSave();

            System.out.println("User entry set up successfully.");

            // Navigate to User Dashboard
            for (Page child : pageRunner.getCurrentPage().getChildrenPage()) {
                if (child.getPageName().equalsIgnoreCase("User Dashboard")) {
                    pageRunner.setPage(child);
                    System.out.println("\nNavigating to " + child.getPageName() + "...");
                    break;
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format.");
        }
    }
}