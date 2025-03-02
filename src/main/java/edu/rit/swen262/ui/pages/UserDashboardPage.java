package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;
import edu.rit.swen262.user.service.DailyHistoryService;
import edu.rit.swen262.user.service.GoalService;
import java.util.ArrayList;
import java.util.List;

public class UserDashboardPage extends Page {
    public UserDashboardPage(PageData pageData) {
        this.pageData = pageData;
        this.pageName = "User Dashboard";
        this.userCommands = new ArrayList<>(); // Generate commands here
    }
}
