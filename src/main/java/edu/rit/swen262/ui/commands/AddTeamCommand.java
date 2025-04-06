package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;

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

        if (pageRunner.getPageData().getTeam(teamName) != null) {
            throw new Exception("Error: Team '" + teamName + "' already exists. Choose a different name.");
        }

        Team newTeam = new Team(teamName);
        pageRunner.getPageData().addTeam(teamName, newTeam);
        SaveData.serializeHistoryToSave();
    }
}
