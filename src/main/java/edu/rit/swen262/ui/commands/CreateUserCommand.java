package edu.rit.swen262.ui.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.UserHistoryService;
import edu.rit.swen262.user.service.UserDataService;

public class CreateUserCommand extends UserCommand {

    private PageData pageData;

    public CreateUserCommand(PageData pageData) {
        super.nameString = "Create";
        super.helpString = "Create [Username] [height] [weight] [Birthday in MM/dd/yyyy]";
        this.pageData = pageData;
    }
    
    /**
     * Method to create a new user
     * @param commandArgs CreateUser [Username, height, weight, MM/dd/yyyy Birthday]
     */
    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length < 5) {
            System.out.println("Error: Invalid number of arguments. Usage: CreateUser [Username] [height] [weight] [Birthday in MM/dd/yyyy]");
            return;
        }

        try {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String username = commandArgs[1];

            if (pageData.getUser(username) != null) {
                System.out.println("Error: User '" + username + "' already exists. Choose a different name.");
                return;
            }

            double height = Double.parseDouble(commandArgs[2]);
            double weight = Double.parseDouble(commandArgs[3]);
            Date birthdate = df.parse(commandArgs[4]);

            User user = new User(username, height, weight, birthdate);
            pageData.addUser(username, user);

            // Create a new user history entry for this user
            UserHistoryService userHistory = new UserHistoryService(username, new Date(), weight, 2000);
            SaveData.addUserHistory(userHistory);
            
            // Update the user data
            UserDataService userDataService = new UserDataService(username, birthdate, height);
            SaveData.addUserData(userDataService);

            System.out.println("\nUser created successfully: " + username);
        } catch (NumberFormatException e) {
            System.out.println("Error: Height and weight must be numeric values.");
        } catch (ParseException e) {
            System.out.println("Error: Invalid date format. Please use MM/dd/yyyy.");
        }
    }


    @Override public String toString() {return super.toString();}
}
