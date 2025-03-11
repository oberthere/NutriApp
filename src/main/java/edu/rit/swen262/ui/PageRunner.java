package edu.rit.swen262.ui;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.rit.swen262.history.PersonalHistory;
import edu.rit.swen262.ui.commands.*;
import edu.rit.swen262.ui.pages.*;

@Component
public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page currentPage;
    private List<UserCommand> globalCommands = new ArrayList<>();
    private Scanner scanner;

    // Default Constructor (Used by Spring Boot)
    public PageRunner() {
        this.pageData = new PageData();
        this.mainPage = createPages();
        this.currentPage = this.mainPage;
        this.scanner = new Scanner(System.in);

        // Register global commands
        registerGlobalCommands();
    }

    private void registerGlobalCommands() {
        globalCommands.add(new ExitCommand(this));
    }

    public void startUp() {
        PersonalHistory.deserializeAndLoadSavedHistory();
        pageData.loadUsersFromHistory();
    }

    
    public void printGlobalCommand() {
        System.out.println("Available Global Commands:");
        for (UserCommand command : globalCommands) {
            System.out.println("  - " + command.getHelp());
        }
    }

    public void setPage(Page page) {this.currentPage = page;}
    public Page getCurrentPage() {return this.currentPage;}

    private void executeCommand(String input) {
        String[] commandArgs = input.split(" ");

        // Validate input
        if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
            System.out.println("Error: No command entered. Please enter a valid command.");
            return;
        }

        // Check local commands
        for (UserCommand command : currentPage.getCommands()) {
            if (command.getName().equalsIgnoreCase(commandArgs[0])) {
                command.performAction(commandArgs);
                return;
            }
        }

        // Check global commands
        for (UserCommand command : globalCommands) {
            if (command.getName().equalsIgnoreCase(commandArgs[0])) {
                command.performAction(commandArgs);
                return;
            }
        }

        System.out.println("Invalid command. Try again.");
    }

    public String getScannerInput(){
        return scanner.nextLine().trim();
    }

    public void closeScanner(){
        scanner.close();

        this.scanner = null;
    }

    public void runPage() {
        
        while (this.scanner != null) {
            this.currentPage.printContent();
            printGlobalCommand();
            this.currentPage.printCommand();


            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            executeCommand(input);

            System.out.println();
            System.out.println();
            System.out.println();
        }
    
    }

    private Page createPages() {
        Page mainPage = new MainPage(pageData, this);
        Page userSetupPage = new UserSetupPage(pageData, this);
        Page userDashboardPage = new UserDashboardPage(pageData);
        Page mealPage = new MealPage(pageData);
        Page historyPage = new HistoryPage(pageData);
        Page workoutPage = new WorkoutPage(pageData);

        // Set up page hierarchy
        mainPage.setChildrenPage(List.of(userSetupPage, userDashboardPage));
        userSetupPage.setParentPage(mainPage);
        userDashboardPage.setParentPage(mainPage);

        userSetupPage.setChildrenPage(List.of(userDashboardPage));
        
        userDashboardPage.setChildrenPage(List.of(mealPage, historyPage, workoutPage));
        mealPage.setParentPage(userDashboardPage);
        historyPage.setParentPage(userDashboardPage);
        workoutPage.setParentPage(userDashboardPage);

        return mainPage;
    }
}
