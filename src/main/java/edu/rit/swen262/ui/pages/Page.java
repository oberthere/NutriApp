package edu.rit.swen262.ui.pages;

import java.util.List;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;

public abstract class Page {
    private Page parentPage;
    private List<Page> childrenPage;
    private List<UserCommand> userCommands;

    public void printContent(PageData pageData) {throw new UnsupportedOperationException("Method not implemented");}
    public void printCommand() {throw new UnsupportedOperationException("Method not implemented");}
    public List<UserCommand> getCommands() {return userCommands;}
    
    public void setParentPage(Page parentPage) {this.parentPage = parentPage;}
    public Page getParentPage() {return this.parentPage;}

    public void setChildrenPage(List<Page> childrenPages) {}
    public List<Page> getChildrenPage() {return this.childrenPage;}

    public String getPageName() {throw new UnsupportedOperationException("Method not implemented");}
}
