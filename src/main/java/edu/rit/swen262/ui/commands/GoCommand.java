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
            returnString += page.getPageName() + " | ";
        }
        returnString = returnString.substring(0, returnString.length() - 3);
        returnString += " ]";
        
        return returnString;
    }

    @Override
    public void performAction(String[] commandArgs) {
        if (commandArgs.length != 2) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        String pageName = commandArgs[1];
        List<Page> childrenPage = pageRunner.getCurrentPage().getChildrenPage();
        boolean pageFound = false;
        
        for (Page page : childrenPage) {
            if (page.getPageName().equalsIgnoreCase(pageName)) {
                pageRunner.setPage(page);
                pageFound = true;
                break;
            }
        }

        // added this to display that the page was not found instead of printing nothing
        if (!pageFound) {
            System.out.println("Error: Invalid Page Name. Usage: " + getHelp());
        }
    }
}
