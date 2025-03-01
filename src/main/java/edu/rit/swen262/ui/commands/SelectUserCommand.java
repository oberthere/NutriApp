package edu.rit.swen262.ui.commands;

import java.util.List;

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
        Page currentPage = pageRunner.getCurrentPage();
        List<Page> childrenPages = currentPage.getChildrenPage();

        // Goes through each children Page to find the UserDashboard
        for (Page childPage : childrenPages) {
            // If user has a daily entry, then navigate to the User Dashboard
            if (user.getDailyHistoryService() != null && childPage.getPageName().equals("UserDashboard")) {
                System.out.println("Navigating to " + childPage.getPageName());
                pageRunner.setPage(childPage);
                break;
            // Else navigate to the user setup
            } else if (user.getDailyHistoryService() == null && childPage.getPageName().equals("UserSetup")) {
                System.out.println("Navigating to " + childPage.getPageName());
                pageRunner.setPage(childPage);
                break;
            }
        }

        pageRunner.runPage();

    }
}
