package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;
import java.util.List;

public class ContinueUserEntryCommand extends UserCommand {
    private PageRunner pageRunner;

    public ContinueUserEntryCommand(PageData pageData, PageRunner pageRunner) {
        super.nameString = "ContinueUserEntry";
        super.helpString = "ContinueUserEntry [meal/workout/history]"; // navifate to desired section
        this.pageRunner = pageRunner;
    }

    @Override
    public void performAction(String[] commandArgs) {

        // ensure length is valid
        if (commandArgs.length < 2) {
            System.out.println("Error: Missing argument. Usage: ContinueUserEntry [meal/workout/history]");
            return;
        }

        String choice = commandArgs[1].toLowerCase();
        Page currentPage = pageRunner.getCurrentPage();
        List<Page> childrenPages = currentPage.getChildrenPage();

        // iterate through the children pages in order to find corresponding page
        for (Page childPage : childrenPages) {
            if ((choice.equals("meal") && childPage.getPageName().equalsIgnoreCase("Meal")) ||
                (choice.equals("workout") && childPage.getPageName().equalsIgnoreCase("Workout")) ||
                (choice.equals("history") && childPage.getPageName().equalsIgnoreCase("History"))) {
                System.out.println("Navigating to " + childPage.getPageName());
                pageRunner.setPage(childPage);
                return;
            }
        }
        
        System.out.println("Error: Invalid choice or corresponding page not found.");
    }
}