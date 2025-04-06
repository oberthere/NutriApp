package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.history.TeamData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public class LeaveTeamCommand extends UserCommand{
    private PageRunner pageRunner;
        
    public LeaveTeamCommand(PageData pageData) {
        this.nameString = "LeaveTeam";
        this.helpString = "LeaveTeam";
        this.pageRunner = pageData.getPageRunner();
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 1) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        User user = pageRunner.getPageData().getCurrentUser();
        Team team = user.getTeam();
        team.removeMember(user);
        user.setTeam(null);
    }
}
