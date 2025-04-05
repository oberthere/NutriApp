package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.TeamPage;

public class SendTeamInviteCommand extends UserCommand {
    private PageRunner pageRunner;
    
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public SendTeamInviteCommand() {
        this.nameString = "SendInvite";
        this.helpString = "SendInvite [Username]";
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 2) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        String userName = commandArgs[1];
        pageRunner.getPageData().getCurrentUser().getTeam().sendInvite(userName);
    }
}
