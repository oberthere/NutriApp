package edu.rit.swen262.ui.pages;

import java.util.List;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.components.DailyHistoryComponent;
import edu.rit.swen262.workout.Workout;
import edu.rit.swen262.ui.commands.AddWorkoutCommand;

public class WorkoutPage extends Page {
    public WorkoutPage(PageData pageData) {
        super(pageData);
        this.pageName = "Workout Page";
        this.userCommands.add(new AddWorkoutCommand(pageData));
    }

    @Override
    public void printContent() {
        DailyHistoryComponent dailyHistory = pageData.getCurrentUser().getDailyHistoryComponent();
        super.printContent();
        System.out.println("Workouts Recorded:");
        printWorkouts(dailyHistory.getWorkouts());
        System.out.println("Workout Suggestion:");
        // based on our current imp, dailyHistory.suggestWorkout() will return null if there are no excess calories
        if (dailyHistory.suggestWorkout() == null) {
            System.out.println("\t- No workout suggestions");
        } else {
            printWorkout(dailyHistory.suggestWorkout());
        }
    }

    private void printWorkouts(List<Workout> workouts) {
        for (Workout workout : workouts) {
            System.out.println("\t- " + workout.getName());
        }
    }

    private void printWorkout(Workout workout) {
        System.out.println("\t- " + workout.getName());
    }
}