package edu.rit.swen262.ui.commands;

import java.util.List;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;

public class GuestUserCommand extends UserCommand {
    
    private PageData pageData;
    private PageRunner pageRunner;

    public GuestUserCommand(PageRunner pageRunner) {
        super.nameString = "Guest";
        super.helpString = "Guest";
        this.pageRunner = pageRunner;
        this.pageData = pageRunner.getPageData();
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs SelectUser [Username]
     */
    @Override
    public void performAction(String[] commandArgs) {


        Page currentPage = pageRunner.getCurrentPage();
        List<Page> childrenPages = currentPage.getChildrenPage();

        // Goes through each children Page to find the UserDashboard
        for (Page childPage : childrenPages) {
            // If user has a user entry, then navigate to the User Dashboard
            if (childPage.getPageName().equals("Guest Page")) {
                System.out.println("Navigating to " + childPage.getPageName());
                pageRunner.setPage(childPage);
                break;
            }
        }   

    }
}