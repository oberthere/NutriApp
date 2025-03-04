package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class UserSetupPage extends Page {
    public UserSetupPage(PageData pageData) {
        super(pageData);
        this.pageName = "User Setup";
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
