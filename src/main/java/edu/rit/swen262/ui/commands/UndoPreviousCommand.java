package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageRunner;

public class UndoPreviousCommand extends UserCommand{

    private PageRunner pr;
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public UndoPreviousCommand(PageRunner pr) {
        this.nameString = "exit";
        this.helpString = "Exit";
        this.pr = pr;
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
        pr.closeScanner();
    }
}
