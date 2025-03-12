package edu.rit.swen262.ui.commands;

import java.util.List;

import edu.rit.swen262.ui.PageData;
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
        
        String returnString = "Go [";
        for (Page page : childrenPage) {
            returnString += page.getPageName() + "|";
        }
        returnString += "]";
        
        return returnString;
    }

    @Override
    public void performAction(String[] commandArgs) {
        String pageName = commandArgs[1];
        List<Page> childrenPage = pageRunner.getCurrentPage().getChildrenPage();
        
        for (Page page : childrenPage) {
            if (page.getPageName().equalsIgnoreCase(pageName)) {
                pageRunner.setPage(page);
                break;
            }
        }
    }
}
