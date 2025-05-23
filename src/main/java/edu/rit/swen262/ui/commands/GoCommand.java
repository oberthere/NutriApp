package edu.rit.swen262.ui.commands;

import java.util.List;

import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.pages.Page;

public class GoCommand extends UserCommand {
    private PageRunner pageRunner;
    
    public GoCommand(PageRunner pageRunner) {
        this.pageRunner = pageRunner;
        super.nameString = "Go";
        super.helpString = "[field not implemented]";
    }

    @Override
    public String getHelp() {
        Page currentPage = pageRunner.getCurrentPage();
        List<Page> childrenPage = currentPage.getChildrenPage();
        
        String returnString = "Go > [ ";
        for (Page page : childrenPage) {
            returnString += page.getPageName().split(" ")[0] + " | ";
        }
        returnString = returnString.substring(0, returnString.length() - 3);
        returnString += " ]";
        
        return returnString;
    }

    @Override
    public void performAction(String[] commandArgs) throws Exception {
        if (commandArgs.length != 2) {
            throw new Exception("Error: Invalid number of arguments. Usage: " + getHelp());
        }

        String pageName = commandArgs[1];
        List<Page> childrenPage = pageRunner.getCurrentPage().getChildrenPage();
        boolean pageFound = false;
        
        for (Page page : childrenPage) {
            if (page.getPageName().split(" ")[0].equalsIgnoreCase(pageName)) {
                System.out.println("\nNavigating to " + page.getPageName());
                pageRunner.setPage(page);
                pageFound = true;
                break;
            }
        }

        // added this to display that the page was not found instead of printing nothing
        if (!pageFound) {
            throw new Exception("Error: Invalid Page Name. Usage: " + getHelp());
        }
    }
}