package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;

public class BackCommand extends UserCommand{
    private PageRunner pageRunner;

    public BackCommand(PageRunner pageRunner) {
        super.nameString = "back";
        super.helpString = "Back"; // navigate to the previous page
        this.pageRunner = pageRunner;
    }

    @Override
    public void performAction(String[] commandArgs) {
        Page currentPage = pageRunner.getCurrentPage();
        Page parentPage = currentPage.getParentPage();

        if (parentPage != null) {
            System.out.println("Going back to " + parentPage.getPageName());
            pageRunner.setPage(parentPage);
        } else {
            System.out.println("Error: No previous page to go back to.");
        }
    }
}

