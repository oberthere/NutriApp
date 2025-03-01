package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class UserSetupPage extends Page {
    private PageData pageData;

    public UserSetupPage(PageData pageData) {
        super.pageName = "User Setup";
        this.pageData = pageData;
    }

    @Override
    public void printContent() {
        super.printContent();
        User user = pageData.getCurrentUser();  
        
        System.out.println("Starting a new day entry for " + user.getName());
    }
}
