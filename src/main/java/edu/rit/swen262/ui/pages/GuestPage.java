package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.GetIngredientsCommand;
import edu.rit.swen262.ui.commands.GetRecipeCommand;


public class GuestPage extends Page {
    public GuestPage(PageData pageData) {
        super(pageData); // Call the parent constructor
        this.pageName = "Guest Page"; // Assign page name
        this.userCommands = new ArrayList<>();
        super.userCommands.add(new GetRecipeCommand());
        super.userCommands.add(new GetIngredientsCommand());
    }

    @Override
    public void printContent() {
        super.printContent();
        }   
}