package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;

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

        int currentWeight;
        int targetWeight;
        int targetCalories;
        boolean physicalFitness;
        

        try {
            currentWeight = Integer.parseInt(commandArgs[1]);
            targetWeight = Integer.parseInt(commandArgs[2]);
            targetCalories = Integer.parseInt(commandArgs[3]);
            physicalFitness = Boolean.parseBoolean(commandArgs[4]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid argument types. Expected: [int] [int] [int] [boolean]");
            return;
        }

        // TODO: Find out what the physical physical fitness is for and changing the type for targetWeight in goal
        // user.startNewDay();

        System.out.println("New goal service created with target weight: " + targetWeight +
                           " and current weight: " + currentWeight +
                           ". Physical fitness priority: " + physicalFitness + ".");
    }
}
