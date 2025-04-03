package edu.rit.swen262.ui.pages;

import java.util.ArrayList;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.GetIngredientsCommand;
import edu.rit.swen262.ui.commands.GetRecipeCommand;
import edu.rit.swen262.ui.commands.SelectUserCommand;
import edu.rit.swen262.user.User;

public class GuestPage extends Page {

    public GuestPage(PageData pageData) {
        super(pageData); // Call the parent constructor
        this.pageName = "Guest Page"; // Assign page name
        this.userCommands = new ArrayList<>();
        super.userCommands.add(new SelectUserCommand(pageData.getPageRunner()));
        super.userCommands.add(new GetRecipeCommand());
        super.userCommands.add(new GetIngredientsCommand());

    }

    @Override
    public void printContent() {
        super.printContent();
        System.out.println("Recorded Users:");
        for (User user : pageData.getAllUsers().values()) { 
            System.out.println("\t- " + user.getName());
        }
        System.out.println();

        super.printContent();
    }


    
}
