package edu.rit.swen262.ui.pages;

import java.util.ArrayList;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class UserSetupPage extends Page {
    private PageData pageData;

    public UserSetupPage(PageData pageData) {
        super(pageData); // Call the parent constructor
        this.pageName = "User Setup"; // Assign page name
        this.userCommands = new ArrayList<>();
    }

    @Override
    public void printContent() {
        super.printContent();
        User user = pageData.getCurrentUser();  
        
        System.out.println("Starting a new day entry for " + user.getName());
    }
}
