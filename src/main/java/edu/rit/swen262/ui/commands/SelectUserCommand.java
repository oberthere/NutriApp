package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.ui.pages.UserDashboardPage;
import edu.rit.swen262.ui.pages.UserSetupPage;
import edu.rit.swen262.user.User;

public class SelectUserCommand extends UserCommand {
    private PageData pageData;
    private PageRunner pageRunner;

    public SelectUserCommand(PageData pageData, PageRunner pageRunner) {
        super.nameString = "SelectUser";
        this.pageData = pageData;
        this.pageRunner = pageRunner;
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs SelectUser [Username]
     */
    @Override
    public void performAction(String[] commandArgs) {
        String username = commandArgs[1];
        User user = pageData.getUser(username);

        pageData.setCurrentUser(user);

        //Send users who started their current day history to userDashboard Page
        if (user.getDailyHistoryService() != null)
        {
            Page dashboardPage = new UserDashboardPage(pageData);
            pageRunner.setPage(dashboardPage);

        }
        //Send users who didn't start on their current day history to userSetup Page
        else {
            Page setupPage = new UserSetupPage(pageData);
            pageRunner.setPage(setupPage);
        }

        pageRunner.runPage();

    }
}
