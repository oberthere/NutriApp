package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;

public class NewUserEntryCommand extends UndoableCommand {
    private PageData pageData;
    private PageRunner pageRunner;

    public NewUserEntryCommand(PageRunner pageRunner) {
        super.nameString = "NewUserEntry";
        super.helpString = "NewUserEntry [currentWeight] [targetWeight] [isPhysicalFitnessGoal]";
        this.pageRunner = pageRunner;
        this.pageData = pageRunner.getPageData();
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length != 4) {
            System.out.println("Error: Invalid number of arguments. Usage: NewUserEntry [currentWeight] [targetWeight] [isPhysicalFitnessGoal]");
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

            // checks that the isPhysicalFitnessGoal is a boolean and then sets it
            if (!commandArgs[3].equalsIgnoreCase("true") && !commandArgs[3].equalsIgnoreCase("false")) {
                System.out.println("Error: Invalid boolean value for 'isPhysicalFitnessGoal'. Please enter 'true' or 'false'.");
                return;
            }
        
            boolean isPhysicalFitnessGoal = commandArgs[3].equalsIgnoreCase("true");

            // Update user user history
            user.startNewDay(currentWeight, targetWeight, isPhysicalFitnessGoal);

            user.getGoalService().getCurrentGoal().calculateTargetCalories(user);

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