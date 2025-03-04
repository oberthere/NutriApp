package edu.rit.swen262.ui.pages;


import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.ui.commands.CreateUserCommand;
import edu.rit.swen262.ui.commands.SelectUserCommand;
import edu.rit.swen262.user.User;

public class MainPage extends Page {
    public MainPage(PageData pageData, PageRunner pageRunner) {
        super(pageData);
        this.pageName = "\nMain Page";
        
        // MainPage Commands
        this.userCommands.add(new CreateUserCommand(pageData));
        this.userCommands.add(new SelectUserCommand(pageData, pageRunner)); 
    }

    @Override
    public void printContent() {
        super.printContent();
        System.out.println("Recorded Users:");
        for (User user : pageData.getAllUsers().values()) { 
            System.out.println("\t- " + user.getName());
        }
        System.out.println();
    }
}
