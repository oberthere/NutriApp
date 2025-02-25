package edu.rit.swen262.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;

public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page currentPage;
    private List<UserCommand> pageCommands;

    public PageRunner(PageData pageData, Page mainPage) {
        this.pageData = pageData;
        this.mainPage = mainPage;
        this.currentPage = mainPage;
    }

    public void setPage(Page page) {
        this.currentPage = page;
    }

    private void executeCommand(List<UserCommand> commands) {
        
        for (UserCommand command : commands) {
            
        }
    }

    public void runPage() {
        this.pageCommands = this.currentPage.getCommands();
        this.currentPage.printContent(this.pageData);
        this.currentPage.printCommand();
        this.executeCommand(this.pageCommands);
    }
}
