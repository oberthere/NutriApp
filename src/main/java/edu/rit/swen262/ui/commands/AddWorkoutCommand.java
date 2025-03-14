package edu.rit.swen262.ui.commands;

import java.util.Locale;
import java.util.Map;

import edu.rit.swen262.other.exception.InvalidMealCreation;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.workout.HighIntensityStrategy;
import edu.rit.swen262.workout.IntensityStrategy;
import edu.rit.swen262.workout.LowIntensityStrategy;
import edu.rit.swen262.workout.MediumIntensityStrategy;
import edu.rit.swen262.workout.Workout;

public class AddWorkoutCommand extends UserCommand {
    public AddWorkoutCommand() {
        super.nameString = "AddWorkout";
        super.helpString = "AddWorkout [workoutName] [durationMin] [intensity]";
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 4) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        String workoutName = commandArgs[1];
        int durationMin = Integer.parseInt(commandArgs[2]);
        IntensityStrategy intensity = getIntensityStrategy(commandArgs[3]);

        Workout newWorkout = new Workout(workoutName, durationMin, intensity);
        // PageData pageData = getPageData();
        // pageData.getCurrentUser().getDailyHistoryService().addWorkout(newWorkout);

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

        // returns the corresponding IntensityStrategy or the default of HighIntensityStrategy if not found
        return intensityStrategyMap.getOrDefault(intensityString, new HighIntensityStrategy());
    }
}
