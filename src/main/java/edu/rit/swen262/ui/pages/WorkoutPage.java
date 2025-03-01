package edu.rit.swen262.ui.pages;

import java.util.List;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.workout.Workout;

public class WorkoutPage extends Page {
    private PageData pageData;

    public WorkoutPage(PageData pageData, List<UserCommand> commands) {
        this.pageData = pageData;
        super.pageName = "Workout";
        super.userCommands = commands;
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
