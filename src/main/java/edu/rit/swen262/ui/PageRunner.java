package edu.rit.swen262.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.pages.Page;
import edu.rit.swen262.user.User;

public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page currentPage;
    private List<UserCommand> pageCommands;

    public PageRunner(Page mainPage) {
        this.pageData = new PageData();
        this.mainPage = mainPage;
    }

    public PageRunner(PageData pageData, Page mainPage) {
        this.pageData = pageData;
        this.mainPage = mainPage;
        this.currentPage = mainPage;
    }

    public void setPage(Page page) {
        this.currentPage = page;
    }

    private void executeCommand(List<UserCommand> commands) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] commandString = input.split(" ");
        System.out.println(commandString);
        for (UserCommand command : commands) {
            if (command.getName().equals(commandString[0])) {
                System.out.println("Running cmd of " + command.getName());
                break;
            }
        }
        scanner.close();
    }

    public void runPage() {
        this.pageCommands = this.currentPage.getCommands();
        this.currentPage.printContent(this.pageData);
        this.currentPage.printCommand();
        this.executeCommand(this.pageCommands);
    }
}
