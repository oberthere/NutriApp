package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class ChangePassword extends UserCommand {
    private PageData pageData;

    public ChangePassword(PageData pageData) {
        super.nameString = "ChangePassword";
        super.helpString = "ChangePassword [New Password]";
        this.pageData = pageData;
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs ChangePassword [New Password]
     */
    @Override
    public void performAction(String[] commandArgs) {
        // Ensure proper length
        if (commandArgs.length != 2) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        User user = pageData.getCurrentUser();
        if (user == null) {
            System.out.println("Error: No user selected.");
            return;
        }

        String password = commandArgs[1];
        if (user.getPassword().equals(password)) {
            System.out.println("That password is already in use. Please try again.");
        } else {
            user.setPassword(password);
            System.out.println("Password sucessfully changed to \"" + password + "\"");
        }
    }
}
