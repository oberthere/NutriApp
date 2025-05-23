package edu.rit.swen262.ui.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.history.UserData;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.components.DailyHistoryComponent;

public class CreateUserCommand extends UserCommand {
    private PageData pageData;

    public CreateUserCommand(PageData pageData) {
        super.nameString = "Create";
        super.helpString = "Create [Username] [Password] [Height in inches] [Weight in pounds] [Birthday in MM/dd/yyyy]";
        this.pageData = pageData;
    }
    
    /**
     * Method to create a new user
     * @param commandArgs CreateUser [Username, Password, Height, Weight, MM/dd/yyyy Birthday]
     */
    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length < 6) {
            throw new Exception("Error: Invalid number of arguments. Usage: CreateUser [Username] [Password] [Height in inches] [Weight in pounds] [Birthday in MM/dd/yyyy]");
        }

        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String username = commandArgs[1];

            if (pageData.getUser(username) != null) {
                throw new Exception("Error: User '" + username + "' already exists. Choose a different name.");
            }

            String password = commandArgs[2];
            double height = Double.parseDouble(commandArgs[3]);
            double weight = Double.parseDouble(commandArgs[4]);
            Date birthdate = df.parse(commandArgs[5]);

            User user = new User(username, password, height, weight, birthdate);
            pageData.addUser(username, user);

            // Create a new user history entry for this user
            DailyHistoryComponent dailyHistory = new DailyHistoryComponent(username, new Date(), weight);
            SaveData.addDailyHistory(dailyHistory);
            
            // Update the user data
            UserData userDataComponent = new UserData(username, password, birthdate, height);
            SaveData.addUserData(userDataComponent);

            System.out.println("\nUser created successfully: " + username);
        } catch (NumberFormatException e) {
            throw new Exception("Error: Height and weight must be numeric values.");
        } catch (ParseException e) {
            throw new Exception("Error: Invalid date format. Please use MM/dd/yyyy.");
        }
    }
}