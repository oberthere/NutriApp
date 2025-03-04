package edu.rit.swen262.ui.pages;

import java.util.ArrayList;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.commands.ContinueUserEntryCommand;
import edu.rit.swen262.ui.commands.NewUserEntryCommand;
import edu.rit.swen262.user.User;

public class UserSetupPage extends Page {
    private PageData pageData;
    private PageRunner pageRunner;
    
    public UserSetupPage(PageData pageData, PageRunner pageRunner) {
        super(pageData);
        this.pageName = "User Setup";
        this.pageData = pageData;
        this.pageRunner = pageRunner; 

        this.userCommands = new ArrayList<>();
        this.userCommands.add(new NewUserEntryCommand(pageData, pageRunner));
    }

    @Override
    public void printContent() {
        super.printContent();

        if (pageData == null) {
            System.out.println("Error: PageData is not initialized.");
            return;
        }

        User user = pageData.getCurrentUser();
        if (user == null) {
            System.out.println("Error: No user selected.");
            return;
        }

        System.out.println("Starting a new day entry for " + user.getName());
    }
}
