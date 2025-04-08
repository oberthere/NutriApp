package edu.rit.swen262.ui.commands;

import java.util.Locale;
import java.util.Map;

import edu.rit.swen262.workout.HighIntensityStrategy;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.workout.LowIntensityStrategy;
import edu.rit.swen262.workout.MediumIntensityStrategy;
import edu.rit.swen262.workout.Workout;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.team.Team;
import edu.rit.swen262.ui.PageData;

public class AddWorkoutCommand extends UserCommand {
    private PageData pageData;

    public AddWorkoutCommand(PageData pageData) {
        super.nameString = "AddWorkout";
        super.helpString = "AddWorkout [workoutName] [durationMin] [intensity]";
        this.pageData = pageData;
    }

    /**
     * Method to add a new workout
     * @param commandArgs AddWorkout [workoutName] [durationMin] [intensity]
     */
    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 4) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        String workoutName = commandArgs[1];

        // parse the duration to make sure its a positive integer
        int durationMin;

        try {
            durationMin = Integer.parseInt(commandArgs[2]);
        } catch (NumberFormatException e) {
            throw new Exception("Error: Duration must be an integer.");
        }
    
        if (durationMin <= 0) {
            throw new Exception("Error: Duration must be a positive integer.");
        }

        // uses the getIntensityStrategy helper method to get the IntensityStrategy
        IntensityStrategy intensity = getIntensityStrategy(commandArgs[3]);

        Workout newWorkout = new Workout(workoutName, durationMin, intensity);

        // get the current user's user history component and add the new workout to it
        DailyHistoryComponent dailyHistory = pageData.getCurrentUser().getDailyHistoryComponent();
        dailyHistory.addWorkout(newWorkout);

        Team team = pageData.getCurrentUser().getTeam();
        for (User user : team.getMembers()) {
            user.incrementTeamNotificationIndex();
        }
        if (team != null) {
            team.addToNotificationLogs(pageData.getCurrentUser(), workoutName);
        team.getChallenge().addToRecord(pageData.getCurrentUser().getName(), durationMin);
        }
        System.out.println(workoutName + " Workout Added Successfully.");
    }

    private IntensityStrategy getIntensityStrategy(String intensityString) {
        // "High", "Medium", or "Low", return the corresponding IntensityStrategy
        Map<String, IntensityStrategy> intensityStrategyMap = Map.of(
            "HIGH", new HighIntensityStrategy(),
            "MEDIUM", new MediumIntensityStrategy(),
            "LOW", new LowIntensityStrategy());

        // makes all caps incase of character differences
        intensityString = intensityString.toUpperCase(Locale.ENGLISH);

        // returns the corresponding IntensityStrategy
        if (!intensityStrategyMap.containsKey(intensityString)) {
            System.out.println("Error: Invalid intensity. Please enter 'High', 'Medium', or 'Low'.");
            return null; // or throw an exception
        }
    
        return intensityStrategyMap.get(intensityString);
    }
}