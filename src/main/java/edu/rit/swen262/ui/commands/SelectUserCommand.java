package edu.rit.swen262.ui.commands;

import java.util.List;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;

public class SelectUserCommand extends UserCommand {
    private PageData pageData;
    private PageRunner pageRunner;

    public SelectUserCommand(PageRunner pageRunner) {
        super.nameString = "Select";
        super.helpString = "Select [Username] [Password]";
        this.pageRunner = pageRunner;
        this.pageData = pageRunner.getPageData();
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs SelectUser [Username] [Password]
     */
    @Override
    public void performAction(String[] commandArgs) throws Exception {
        // Ensure proper length
        if (commandArgs.length < 3) {
            throw new Exception("Error: Please provide a username and password. Usage: Select [Username] [Password]");
        } else if (commandArgs.length != 3) {
            throw new Exception("Error: Too many arguments. Usage: Select [Username] [Password]");
        }

        String username = commandArgs[1];
        String password = commandArgs[2];
        User user = pageData.getUser(username);

        // If a username does not exist, return error
        if (user == null) {
            throw new Exception("Error: User '" + username + "' not found.");
        }
        if (!password.equals(user.getPassword())) {
            throw new Exception("Error: Invalid Password");
        }

        pageData.setCurrentUser(user);
        Page currentPage = pageRunner.getCurrentPage();
        List<Page> childrenPages = currentPage.getChildrenPage();

        // Goes through each children Page to find the UserDashboard
        for (Page childPage : childrenPages) {
            // If user has a user entry, then navigate to the User Dashboard
            if (user.getDailyHistoryComponent() != null && childPage.getPageName().equals("User Dashboard")) {
                System.out.println("\nFound Entry For Today...");
                System.out.println("Navigating to " + childPage.getPageName());
                pageRunner.setPage(childPage);
                break;
            // Else navigate to the user setup
            } else if (user.getDailyHistoryComponent() == null && childPage.getPageName().equals("User Setup Page")) {
                System.out.println("\nNo Entry Found For Today...");
                System.out.println("Navigating to " + childPage.getPageName());
                pageRunner.setPage(childPage);
                break;
            }
        }
    }
}