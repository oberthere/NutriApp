package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;

public class GoBackCommand extends UserCommand{
    private PageRunner pageRunner;

    public GoBackCommand(PageRunner pageRunner) {
        super.nameString = "GoBack";
        super.helpString = "GoBack"; // navigate to the previous page
        this.pageRunner = pageRunner;
    }

    @Override
    public void performAction(String[] commandArgs) {
        Page currentPage = pageRunner.getCurrentPage();
        Page parentPage = currentPage.getParentPage();

        if (parentPage != null) {
            System.out.println("Navigating back to " + parentPage.getPageName());
            pageRunner.setPage(parentPage);
        } else {
            System.out.println("Error: No previous page to go back to.");
        }
    }
}

