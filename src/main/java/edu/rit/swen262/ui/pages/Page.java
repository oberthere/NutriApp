package edu.rit.swen262.ui.pages;

import java.util.List;

import org.springframework.shell.boot.SpringShellProperties.Command;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;

public abstract class Page {
    protected PageData pageData;
    protected Page parentPage;
    protected String pageName;
    protected List<Page> childrenPage;
    protected List<UserCommand> userCommands;

    public void printContent() {
        String pageLink = pageName;
        Page page = parentPage;
        while (page.getParentPage() != null)
        {
            pageLink = page.getParentPage().getPageName() + ">" + pageLink;
            page = page.parentPage;
        }
        System.out.println(pageLink);
        System.out.println();
    }
    
    public void printCommand() {
        for (UserCommand userCommand: userCommands) {
            System.out.println("    - " + userCommand);
        }
    }
    public List<UserCommand> getCommands() {return userCommands;}
    
    public void setParentPage(Page parentPage) {this.parentPage = parentPage;}
    public Page getParentPage() {return this.parentPage;}

    public void setChildrenPage(List<Page> childrenPages) {}
    public List<Page> getChildrenPage() {return this.childrenPage;}

    public String getPageName() {return this.pageName;}
}
