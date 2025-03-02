package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import java.util.List;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.workout.Workout;

public class WorkoutPage extends Page {
    public WorkoutPage(PageData pageData) {
        super(pageData);
        this.pageName = "Workout";
        this.userCommands = new ArrayList<>();
    }

    @Override
    public void printContent() {
        DailyHistoryService dh = pageData.getCurrentUser().getDailyHistoryService();

        super.printContent();
        System.out.println("Workouts Recorded:");
        printWorkouts(dh.getWorkouts());
    }

    private void printWorkouts(List<Workout> workouts) {
        for (Workout workout : workouts) {
            System.out.println("\t- " + workout.getName());
        }
    }
}
