package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;

public class NewUserEntryCommand extends UndoableCommand<Object> {
    private PageData pageData;
    private PageRunner pageRunner;

    public NewUserEntryCommand(PageRunner pageRunner) {
        super.nameString = "NewUserEntry";
        super.helpString = "NewUserEntry [currentWeight in pounds] [targetWeight in pounds] [isPhysicalFitnessGoal]";
        this.pageRunner = pageRunner;
        this.pageData = pageRunner.getPageData();
    }

    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 4) {
            throw new Exception("Error: Invalid number of arguments. Usage: NewUserEntry [currentWeight in pounds] [targetWeight in pounds] [isPhysicalFitnessGoal]");
        }

        User user = pageData.getCurrentUser();
        if (user == null) {
            throw new Exception("Error: No user selected.");
        }

        try {
            // checks that the currentWeight is a positive num
            double currentWeight = Double.parseDouble(commandArgs[1]);
            if (currentWeight <= 0) {
                throw new Exception("Error: Current weight must be a positive number.");
            }

            // checks that the targetWeight is a positive num
            double targetWeight = Double.parseDouble(commandArgs[2]);
            if (targetWeight <= 0) {
                throw new Exception("Error: Target weight must be a positive number.");
            }

            // checks that the isPhysicalFitnessGoal is a boolean and then sets it
            if (!commandArgs[3].equalsIgnoreCase("true") && !commandArgs[3].equalsIgnoreCase("false")) {
                throw new Exception("Error: Invalid boolean value for 'isPhysicalFitnessGoal'. Please enter 'true' or 'false'.");
            }
        
            boolean isPhysicalFitnessGoal = commandArgs[3].equalsIgnoreCase("true");

            // Update user user history
            user.startNewDay(currentWeight, targetWeight, isPhysicalFitnessGoal);

            user.getGoalComponent().getCurrentGoal().calculateTargetCalories(user);

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
            throw new Exception("Error: Invalid input format.");
        }
    }
    
    @Override
    public void undo() throws Exception {
      User user = pageData.getCurrentUser();
      user.undoStartDay();
    }
}