package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.GoalService;

public class NewUserEntryCommand extends UserCommand {
    private PageData pageData;

    public NewUserEntryCommand(PageData pageData, PageRunner pageRunner) {
        super.nameString = "NewUserEntry";
        super.helpString = "NewUserEntry [currentWeight] [targetWeight] [physicalFitness] - Set weight and create a new goal service for the day";
        this.pageData = pageData;
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 4) {
            System.out.println("Error: Invalid number of arguments. Usage: NewUserEntry [currentWeight] [targetWeight] [physicalFitness]");
            return;
        }

        User user = pageData.getCurrentUser();
        if (user == null) {
            System.out.println("Error: No user selected.");
            return;
        }

        int currentWeight;
        int targetWeight;
        boolean physicalFitness;

        try {
            currentWeight = Integer.parseInt(commandArgs[1]);
            targetWeight = Integer.parseInt(commandArgs[2]);
            physicalFitness = Boolean.parseBoolean(commandArgs[3]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument types. Expected: [int] [int] [boolean]");
            return;
        }

        // Create a new GoalService based on user input and store it in the user
        GoalService goalService = new GoalService(physicalFitness, targetWeight, currentWeight);
        user.setGoalService(goalService);

        System.out.println("New goal service created with target weight: " + targetWeight +
                           " and current weight: " + currentWeight +
                           ". Physical fitness priority: " + physicalFitness + ".");
    }
}
