package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.PersonalHistory;

public class ExitCommand extends UserCommand{

    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public ExitCommand() {
        this.nameString = "exit";
        this.helpString = "Exit application and save data";
    }

    @Override 
    public void performAction(String[] commandArgs){
        System.out.println("Saving date...");
        PersonalHistory.serializeHistoryToSave(); // save user history before exiting
        System.out.println("Data saved. Exiting application...");
        System.exit(0);
    }
}
