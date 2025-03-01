package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public class NewUserEntryCommand extends UserCommand {
    private PageData pageData;

    public NewUserEntryCommand(PageData pageData, PageRunner pageRunner) {
        super.nameString = "NewUserEntry";
        super.helpString = "NewUserEntry [currentWeight] [targetWeight] [targetCalories] [physicalFitness]";
        this.pageData = pageData;
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 5) {
            System.out.println("Error: Invalid number of arguments. Usage: NewUserEntry [currentWeight] [targetWeight] [targetCalories] [physicalFitness]");
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
            boolean physicalFitness = commandArgs[4].equalsIgnoreCase("true") || commandArgs[4].equalsIgnoreCase("yes");

            // Adjust calories if user prioritizes fitness
            if (physicalFitness) {
                targetCalories += 500; // Example adjustment
            }

            System.out.println("New goal set: Target weight = " + targetWeight + 
                               ", Current weight = " + currentWeight + 
                               ", Target Calories = " + targetCalories + 
                               ", Physical fitness priority = " + physicalFitness);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument types. Expected: [double] [double] [int] [boolean]");
        }
    }
}
