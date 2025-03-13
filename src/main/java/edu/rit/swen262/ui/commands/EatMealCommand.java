package edu.rit.swen262.ui.commands;

public class EatMealCommand extends UserCommand {
    public EatMealCommand() {
        super.nameString = "EatMeal";
        super.helpString = "EatMeal [mealName]";
    }

    @Override
    public void performAction(String[] commandArgs) {
        // TODO Auto-generated method stub
        super.performAction(commandArgs);
    }
}
