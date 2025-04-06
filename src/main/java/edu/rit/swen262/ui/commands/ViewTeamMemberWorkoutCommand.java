package edu.rit.swen262.ui.commands;

import java.util.Map;

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
        return super.getHelp();
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 1) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        User user = pageRunner.getPageData().getCurrentUser();
        Team team = user.getTeam();
        System.out.println("Team Workouts:");
        for (Map.Entry<User, String> entry : team.getNotificationLogs()) {
            System.out.println("  - " + entry.getKey().getName() + ": " + entry.getValue());
        }

        user.resetTeamNotificationIndex();
    }
}
