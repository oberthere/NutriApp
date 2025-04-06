package edu.rit.swen262.ui.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.rit.swen262.team.challenge.Challenge;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class CreateChallengeCommand extends UserCommand {
    private PageData pageData;

    public CreateChallengeCommand(PageData pageData) {
        super.nameString = "CreateChallenge";
        super.helpString = "CreateChallenge [Name] [MM/dd/yyyy EndDate] [Instructions (use underscores instead_of_spaces)]";
        this.pageData = pageData;
    }

    /**
     * Method to create a new team challenge
     * @param commandArgs CreateChallenge [Name] [MM/dd/yyyy EndDate] [Instructions as a single string]
     */
    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length < 4) {
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

            // Join remaining args as instructions (allows for spaces if underscores used)
            StringBuilder instructionBuilder = new StringBuilder();
            for (int i = 3; i < commandArgs.length; i++) {
                instructionBuilder.append(commandArgs[i]);
                if (i != commandArgs.length - 1) {
                    instructionBuilder.append(" ");
                }
            }

            String rawInstructions = instructionBuilder.toString();
            String instructions = rawInstructions.replace("_", " ");

            Challenge challenge = new Challenge(challengeName, endDate, instructions);
            Team team = currentUser.getTeam();
            team.makeChallenge(challenge);

            System.out.println("\nChallenge created successfully: " + challengeName);
        } catch (ParseException e) {
            throw new Exception("Error: Invalid date format. Please use MM/dd/yyyy.");
        }
    }
}
