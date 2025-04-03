package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageRunner;

public class ExitCommand extends UserCommand{
    private PageRunner pageRunner;
    
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public ExitCommand(PageRunner pageRunner) {
        this.nameString = "exit";
        this.helpString = "Exit";
        this.pageRunner = pageRunner;
    }

    @Override 
    public void performAction(String[] commandArgs){
        if (commandArgs.length != 1) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        System.out.println("Saving date...");
        SaveData.serializeHistoryToSave(); // save user history before exiting
        System.out.println("Data saved. Exiting application...");
        pageRunner.closeScanner();
    }
}