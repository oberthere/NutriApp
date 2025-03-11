package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.ui.PageRunner;

public class ExitCommand extends UserCommand{

    private PageRunner pr;
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public ExitCommand(PageRunner pr) {
        this.nameString = "exit";
        this.helpString = "Exit";
        this.pr = pr;
    }

    @Override 
    public void performAction(String[] commandArgs){
        System.out.println("Saving date...");
        PersonalHistory.serializeHistoryToSave(); // save user history before exiting
        System.out.println("Data saved. Exiting application...");
        pr.closeScanner();
    }
}
