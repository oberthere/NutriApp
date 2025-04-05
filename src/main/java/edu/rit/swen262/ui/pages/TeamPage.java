package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;

public class TeamPage extends Page {
    private String pageName;
    private UserCommand createTeamInviteCommand;

    public TeamPage(PageData pageData) {
        super(pageData);
        this.pageName = "TeamPage";
        
        
    }
}
