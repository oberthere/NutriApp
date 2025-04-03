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

    public Page getParentPage() {return this.parentPage;}
    public String getPageName() {return this.pageName;}
    public List<Page> getChildrenPage() {return this.childrenPage;}
    public List<UserCommand> getCommands() {return this.userCommands;}

    public void setParentPage(Page parentPage) {this.parentPage = parentPage;}
    public void setChildrenPage(List<Page> childrenPages) {this.childrenPage = childrenPages;}

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
}