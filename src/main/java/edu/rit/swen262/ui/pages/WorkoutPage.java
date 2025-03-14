package edu.rit.swen262.ui.pages;

import java.util.List;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.workout.Workout;
import edu.rit.swen262.ui.commands.AddWorkoutCommand;

public class WorkoutPage extends Page {
    public WorkoutPage(PageData pageData) {
        super(pageData);
        this.pageName = "Workout";
        this.userCommands.add(new AddWorkoutCommand());
    }

    @Override
    public void printContent() {
        DailyHistoryService dh = pageData.getCurrentUser().getDailyHistoryService();
        super.printContent();
        System.out.println("Workouts Recorded:");
        printWorkouts(dh.getWorkouts());
        System.out.println("Workout Suggestion:");
        // based on our current imp, dh.suggestWorkout() will return null if there are no excess calories
        if (dh.suggestWorkout() == null) {
            System.out.println("\t- No workout suggestions");
        } else {
            printWorkout(dh.suggestWorkout());
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
