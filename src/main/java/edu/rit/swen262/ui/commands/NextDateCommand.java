package edu.rit.swen262.ui.commands;

import java.util.Date;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.DailyHistoryComponent;

public class NextDateCommand extends UserCommand{
    private PageData pageData;


    public NextDateCommand(PageData pageData) {
        super.nameString = "NextDate";
        super.helpString = "Next Date";
        this.pageData = pageData;
    }


    @Override
    public void performAction(String[] commandArgs) {
       
        if (commandArgs.length != 1) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }
        try {

            Date currentDate = PageData.getCurrentDate();

            // Save current day data
            SaveData.serializeHistoryToSave(); // save user history before exiting);
   
            // Move to next day (+1 day in ms)
            Date nextDate = new Date(currentDate.getTime() + (1000 * 60 * 60 * 24));
            PageData.setCurrentDate(nextDate);
       
            // Reset data for all users
            for (User user : pageData.getAllUsers().values()) {
                // Create a new DailyHistoryComponent for the user
                DailyHistoryComponent newHistory = new DailyHistoryComponent(user.getName(), nextDate, user.getWeight());

                // Set the new daily history component for the user
                user.setDailyHistoryComponent(newHistory);
            }

            System.out.println("Advanced to the next date: " + nextDate);

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }


}


