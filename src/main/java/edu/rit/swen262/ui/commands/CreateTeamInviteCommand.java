package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageRunner;

public class CreateTeamInviteCommand extends UserCommand {
        private PageRunner pageRunner;
    
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public CreateTeamInviteCommand() {
        this.nameString = "CreateInvite";
        this.helpString = "CreateInvite ";
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 1) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        System.out.println("Saving date...");
        SaveData.serializeHistoryToSave(); // save user history before exiting
        System.out.println("Data saved. Exiting application...");
        pageRunner.closeScanner();
    }
}
