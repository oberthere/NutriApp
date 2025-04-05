package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.TeamPage;

public class AddTeamCommand extends UserCommand {
    private PageRunner pageRunner;
        
    
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public AddTeamCommand(PageData pageData) {
        this.nameString = "AddTeam";
        this.helpString = "AddTeam [TeamName]";
        this.pageRunner = pageData.getPageRunner();
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 2) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        String teamName = commandArgs[1];
        Team newTeam = new Team(teamName);
        pageRunner.getPageData().addTeam(teamName, newTeam);
        SaveData.serializeHistoryToSave();
    }
}
