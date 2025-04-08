package edu.rit.swen262.ui.commands;

import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;

public class BackCommand extends UserCommand{
    private PageRunner pageRunner;

    public BackCommand(PageRunner pageRunner) {
        super.nameString = "Back";
        super.helpString = "Back"; // navigate to the previous page
        this.pageRunner = pageRunner;
    }

    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 1) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        Page currentPage = pageRunner.getCurrentPage();
        Page parentPage = currentPage.getParentPage();

        if (parentPage != null) {
            SaveData.serializeHistoryToSave();
            System.out.println("\nGoing back to " + parentPage.getPageName());
            pageRunner.setPage(parentPage);
        } else {
            throw new Exception("Error: No previous page to go back to.");
        }
    }
}