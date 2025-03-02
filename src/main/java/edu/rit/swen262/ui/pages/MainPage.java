package edu.rit.swen262.ui.pages;

import java.util.ArrayList;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.User;

public class MainPage extends Page {
    public MainPage(PageData pageData) {
        super(pageData);
        this.pageName = "Main Page";
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
