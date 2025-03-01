package edu.rit.swen262.ui.pages;

import java.util.List;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.user.User;

public class MainPage extends Page {
    private PageData pageData;

    public MainPage(PageData pageData, List<UserCommand> commands) {
        this.pageData = pageData;
        super.pageName = "Main Page";
        super.userCommands = commands;
    }

    @Override
    public void printContent() {
        super.printContent();
        System.out.println("Recorded Users:");
        for (User user : pageData.getAllUsers()) {
            System.out.println("\t- " + user.getName());
        }
        System.out.println();
    }    
}
