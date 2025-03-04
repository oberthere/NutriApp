package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public class NewUserEntryCommand extends UserCommand {
    private PageData pageData;

    public NewUserEntryCommand(PageData pageData, PageRunner pageRunner) {
        super.nameString = "NewUserEntry";
        super.helpString = "NewUserEntry [currentWeight] [targetWeight] [targetCalories] [isPhysicalFitnessGoal]";
        this.pageData = pageData;
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

            user.startNewDay(currentWeight, targetWeight, targetCalories, isPhysicalFitnessGoal);

            System.out.println("Daily entry set up successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input format.");
        }
    }
}
