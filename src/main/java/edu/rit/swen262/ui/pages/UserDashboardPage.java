package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public class UserDashboardPage extends Page {
    private PageData pageData;

    public UserDashboardPage(PageData pageData) {
        this.pageData = pageData;
    }

    @Override
    public void printContent() {
        super.printContent();

        User user = pageData.getCurrentUser();
        
    }
}
