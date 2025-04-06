package edu.rit.swen262.ui.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.challenge.PushUpChallengeCreator;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class CreateChallengeCommand extends UserCommand {
    private PageData pageData;

    public CreateChallengeCommand(PageData pageData) {
        super.nameString = "CreateChallenge";
        super.helpString = "CreateChallenge [Pushup | Biking] [MM/dd/yyyy EndDate]";
        this.pageData = pageData;
    }

    /**
     * Method to create a new team challenge
     * @param commandArgs CreateChallenge [Name] [MM/dd/yyyy EndDate] [Instructions as a single string]
     */
    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length < 3) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + helpString);
        }

        User currentUser = pageData.getCurrentUser();
        if (currentUser == null || currentUser.getTeam() == null) {
            throw new Exception("Error: You must be in a team to create a challenge.");
        }

        try {
            String challengeName = commandArgs[1];

            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date endDate = df.parse(commandArgs[2]);

        

            Team team = currentUser.getTeam();
           
            if (challengeName.equalsIgnoreCase("PushUps") || challengeName.equalsIgnoreCase("PushUp" )){
                team.setChallengeCreator(new PushUpChallengeCreator());
            }

            team.makeChallenge(endDate);

            System.out.println("\nChallenge created successfully: " + challengeName);
        } catch (ParseException e) {
            throw new Exception("Error: Invalid date format. Please use MM/dd/yyyy.");
        }
    }

    @Override
    public String getHelp() {
        
        return super.getHelp();
    }
}
