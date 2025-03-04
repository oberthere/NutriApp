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
    private final Scanner scanner;

    // Default Constructor (Used by Spring Boot)
    public PageRunner() {
        this.pageData = new PageData();
        this.mainPage = new MainPage(pageData, this);
        this.currentPage = this.mainPage;
        this.scanner = new Scanner(System.in);

        // Register global commands
        registerGlobalCommands();
    }

    public PageRunner(PageData pageData, Page mainPage) {
        this.pageData = pageData;
        this.mainPage = mainPage;
        this.currentPage = mainPage;
        this.scanner = new Scanner(System.in);

        // Register global commands
        registerGlobalCommands();
    }

    private void registerGlobalCommands() {
        globalCommands.add(new CreateUserCommand(pageData));
        globalCommands.add(new SelectUserCommand(pageData, this));
    }

    public void startUp() {
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

        currentPage = mainPage;

        PersonalHistory.deserializeAndLoadSavedHistory();
        pageData.loadUsersFromHistory();
    }

    public void setPage(Page page) {
        this.currentPage = page;
    }

    public Page getCurrentPage() {
        return this.currentPage;
    }

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

    public void runPage() {
        while (true) {
            this.currentPage.printContent();
            this.currentPage.printCommand();

            System.out.println("  - exit"); // exit should always be displayed as an available command

            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Saving data...");
                PersonalHistory.serializeHistoryToSave();  // Save user history before exit
                System.out.println("Data saved. Exiting application...");
                break;
            }

            boolean navigated = false;
            for (Page child : currentPage.getChildrenPage()) {
                if (child.getPageName().equalsIgnoreCase(input)) {
                    currentPage = child;
                    System.out.println("\nNavigating to " + child.getPageName() + "...");
                    navigated = true;
                    break;
                }
            }

            if (input.equalsIgnoreCase("back") && currentPage.getParentPage() != null) {
                System.out.println("\nGoing back to " + currentPage.getParentPage().getPageName() + "...");
                currentPage = currentPage.getParentPage();
                navigated = true;
            }

            if (!navigated) {
                executeCommand(input);
            }

            System.out.println();
            System.out.println();
            System.out.println();
        }
        
        scanner.close();
    }
}
