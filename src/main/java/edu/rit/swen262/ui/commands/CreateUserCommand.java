package edu.rit.swen262.ui.commands;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class CreateUserCommand extends UserCommand {

    private PageData pageData;

    public CreateUserCommand(PageData pageData) {
        super.nameString = "CreateUser";
        super.helpString = "CreateUser [Username] [height] [weight] [Birthday in MM/dd/yyyy]";
        this.pageData = pageData;
    }
    
    /**
     * Method to create a new user
     * @param commandArgs [CreateUser, Username, height, weight, MM/dd/yyyy Birthday]
     */
    @Override
    public void performAction(String[] commandArgs) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        String username = commandArgs[1];
        double height = Double.parseDouble(commandArgs[2]);
        double weight = Double.parseDouble(commandArgs[3]);
        Date birthdate = null;

        try { birthdate = df.parse(commandArgs[4]);} 
        catch (ParseException e) {
            System.out.println("Unable to Create User. Invalid birthday format");
            e.printStackTrace();
        }

        //TODO: Check if the user is in the personalHistory record.
        // if (PersonalHistory.getHistory().containsKey(username)) {
        //     for (DailyHistoryService dh : PersonalHistory.getHistory().get(username)) {
                
        //     }
        // }
            //If yes, add the user's current day daily history if they have it.

        //Else make a new user
        User user = new User(username, height, weight, birthdate);
        pageData.addUser(nameString, user);
        
    }

    @Override public String toString() {return super.toString();}
}
