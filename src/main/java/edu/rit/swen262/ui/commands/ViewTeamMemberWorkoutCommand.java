package edu.rit.swen262.ui.commands;

import java.util.Map;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.history.TeamData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public class ViewTeamMemberWorkoutCommand extends UserCommand {
    private PageRunner pageRunner;
        
    
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public ViewTeamMemberWorkoutCommand(PageData pageData) {
        this.nameString = "ViewWorkouts";
        this.helpString = "ViewWorkouts";
        pageRunner = pageData.getPageRunner();
    }


    @Override
    public String getHelp() {
        // TODO Auto-generated method stub
        return super.getHelp();
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 1) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        TeamData teamData = new TeamData(pageRunner.getPageData().getCurrentUser().getTeam());
        System.out.println("Team Workouts:");
        for (Map.Entry<String, String> entry : teamData.getNotificationLogs()) {
            System.out.println("  - " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
