package edu.rit.swen262.ui.commands;

public class AddMealCommand extends UserCommand {
    public AddMealCommand() {
        super.nameString = "AddMeal";
        super.helpString = "AddMeal [name] [recipeName] [recipeName] ... [breakfast|lunch|dinner]";
    }

    @Override
    public void performAction(String[] commandArgs) {
        // TODO Auto-generated method stub
        super.performAction(commandArgs);
    }
}
