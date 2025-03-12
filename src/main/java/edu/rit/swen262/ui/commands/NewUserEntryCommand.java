package edu.rit.swen262.ui.commands;

import java.util.ArrayList;
import java.util.Date;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;

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
        if (commandArgs.length < 5) {
            System.out.println("Error: Invalid number of arguments. Usage: NewUserEntry [currentWeight] [targetWeight] [targetCalories] [isPhysicalFitnessGoal]");
            return;
        }

        User user = pageData.getCurrentUser();
        if (user == null) {
            System.out.println("Error: No user selected.");
            return;
        }

        try {
            double currentWeight = Double.parseDouble(commandArgs[1]);
            double targetWeight = Double.parseDouble(commandArgs[2]);
            int targetCalories = Integer.parseInt(commandArgs[3]);
            boolean isPhysicalFitnessGoal = Boolean.parseBoolean(commandArgs[4]);

            // Update user daily history
            user.startNewDay(currentWeight, targetWeight, targetCalories, isPhysicalFitnessGoal);

            // Create a new DailyHistoryService entry
            DailyHistoryService dailyHistory = new DailyHistoryService(user.getName(), new Date(), user.getHeight(), currentWeight, user.getBirthdate(), targetCalories);

            // Ensure user history exists
            if (!PersonalHistory.getHistory().containsKey(user.getName())) {
                PersonalHistory.getHistory().put(user.getName(), new ArrayList<>());
            }

            // Add the new daily history entry
            PersonalHistory.getHistory().get(user.getName()).add(dailyHistory);

            // Save the updated history to file
            PersonalHistory.serializeHistoryToSave();

            System.out.println("Daily entry set up successfully.");
            
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
