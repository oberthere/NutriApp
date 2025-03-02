package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import java.util.ArrayList;

public class UserDashboardPage extends Page {
    public UserDashboardPage(PageData pageData) {
        super(pageData); // Call the parent constructor
        this.pageName = "User Dashboard"; // Assign page name
        this.userCommands = new ArrayList<>();
    }

    @Override
    public void printContent() {
        super.printContent();
        System.out.println("User Dashboard Page");
    }
}
