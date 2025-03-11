package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import java.util.List;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;

public abstract class Page {
    protected PageData pageData;
    protected Page parentPage;
    protected String pageName;
    protected List<Page> childrenPage = new ArrayList<>();
    protected List<UserCommand> userCommands = new ArrayList<>();

    public Page(PageData pageData) {
        this.pageData = pageData;
    }

    public void printContent() {
        String pageLink = pageName;
        Page page = parentPage;
        while (page != null) {
            pageLink = page.getPageName() + " > " + pageLink;
            page = page.getParentPage();
        }
        System.out.println(pageLink);
        System.out.println();
    }

    public void printCommand() {
        System.out.println("Available Page Commands:");
        for (UserCommand command : userCommands) {
            System.out.println("  - " + command.getHelp());
        }
    }

    public List<UserCommand> getCommands() { return userCommands; }

    public void setParentPage(Page parentPage) { 
        this.parentPage = parentPage;
        if (!userCommands.stream().anyMatch(cmd -> cmd.getName().equalsIgnoreCase("back"))) {
            userCommands.add(new GoBackCommand());
        }
    }

    public Page getParentPage() { return this.parentPage; }

    public void setChildrenPage(List<Page> childrenPages) { this.childrenPage = childrenPages; }

    public List<Page> getChildrenPage() { return this.childrenPage; }

    public String getPageName() { return this.pageName; }

    private class GoBackCommand extends UserCommand {
        public GoBackCommand() {
            this.nameString = "back";
            this.helpString = "Go back to the previous page.";
        }

        @Override
        public void performAction(String[] commandArgs) {
            if (parentPage != null) {
                System.out.println("Going back to " + parentPage.getPageName());
            }
        }
    }
}
