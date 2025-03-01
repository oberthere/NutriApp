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

        double currentWeight;
        double targetWeight;
        int targetCalories;
        boolean isPhysicalFitness;
        

        try {
            currentWeight = Integer.parseInt(commandArgs[1]);
            targetWeight = Integer.parseInt(commandArgs[2]);
            targetCalories = Integer.parseInt(commandArgs[3]);
            isPhysicalFitness = Boolean.parseBoolean(commandArgs[4]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument types. Expected: [double] [double] [int] [boolean]");
            return;
        }

        user.startNewDay(currentWeight, targetWeight, targetCalories, isPhysicalFitness);

        System.out.println("New goal service created with target weight: " + targetWeight +
                           " and current weight: " + currentWeight +
                           ". Physical fitness priority: " + isPhysicalFitness + ".");
    }
}
