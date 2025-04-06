package edu.rit.swen262.ui.commands;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.TeamInvite;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public class JoinTeamCommand extends UserCommand {
    private PageRunner pageRunner;

    public JoinTeamCommand(PageData pageData) {
        this.nameString = "JoinTeam";
        this.helpString = "JoinTeam [TeamName]";
        this.pageRunner = pageData.getPageRunner();
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 2) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        String teamName = commandArgs[1];
        User user = pageRunner.getPageData().getCurrentUser();
        Team team = pageRunner.getPageData().getTeam(teamName);

        if (team != null) {
            throw new Exception("Error: Team '" + teamName + "' does not exists. Try a different name.");
        }

        for (TeamInvite invite : user.getTeamInvite()) {
            if (invite.getTeamName().equals(teamName)) {
                pageRunner.getPageData().getTeam(teamName).acceptMember(user);
            }
        }
    }
}
