package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageRunner;

public class ViewTeamMemberWorkoutCommand extends UserCommand {
    private PageRunner pageRunner;
        
    
    /** 
    * ExitCommand handles the exit functionality as a standalone command.
    */
    public ViewTeamMemberWorkoutCommand() {
        this.nameString = "ViewWorkouts";
        //this.helpString = "ViewWorkouts [TeamName]";
        this.helpString = "[Not implemented]";
    }


    @Override
    public String getHelp() {
        // TODO Auto-generated method stub
        return super.getHelp();
    }

    @Override 
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 2) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

    }
}
