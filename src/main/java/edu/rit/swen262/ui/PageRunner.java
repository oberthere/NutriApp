package edu.rit.swen262.ui;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import edu.rit.swen262.csv.csvReader;
import edu.rit.swen262.history.SaveData;
import edu.rit.swen262.ui.commands.*;
import edu.rit.swen262.ui.pages.*;

@Component
public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page currentPage;
    private List<UserCommand> globalCommands = new ArrayList<>();
    private Stack<UndoableCommand<Object>> undoableCommandHistory = new Stack<>();
    private Scanner scanner;

    public PageRunner() {
        this.pageData = new PageData(this);
        this.mainPage = createPages();
        this.currentPage = this.mainPage;
        this.scanner = new Scanner(System.in);

        // Register global commands
        registerGlobalCommands();
    }

    public Page getMainPage() {return this.mainPage;}
    public PageData getPageData() {return this.pageData;}
    public Page getCurrentPage() {return this.currentPage;}
    public String getScannerInput(){return scanner.nextLine().trim();}

    public void setPage(Page page) {this.currentPage = page;}
    public void closeScanner() {scanner.close();this.scanner = null;}
    
    public boolean isCommandHistoryEmpty() {
        return undoableCommandHistory.isEmpty();
    }

    public UndoableCommand<Object> popLastUndoableCommand() {
        if (!undoableCommandHistory.isEmpty()) {
            return undoableCommandHistory.pop();
        }
        return null;
    }

    public void printGlobalCommand() {
        // System.out.println("Available Global Commands:");
        for (UserCommand command : globalCommands) {
            System.out.println("  - " + command.getHelp());
        }
        if (!undoableCommandHistory.isEmpty()) {
            System.out.println("  - Undo - " + undoableCommandHistory.peek().getName());
        }
    }

    private void registerGlobalCommands() {
        globalCommands.add(new BackCommand(this));
        globalCommands.add(new ExitCommand(this));
        globalCommands.add(new GoHomeCommand(this));
    }

    @SuppressWarnings("unchecked")
    private void executeCommand(String input) throws Exception {
        String[] commandArgs = input.split(" ");

        // Validate input
        if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
            System.out.println("Error: No command entered. Please enter a valid command.");
            return;
        }

        // Check local commands
        for (UserCommand command : currentPage.getCommands()) {
            if (command.isActive() == false) {continue;}
            if (command.getName().equalsIgnoreCase(commandArgs[0])) {
                command.performAction(commandArgs);
                if (command instanceof UndoableCommand) {
                    undoableCommandHistory.push((UndoableCommand<Object>) command);
                }
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
        
        // Check undo command
        UndoPreviousCommand undoCommand = new UndoPreviousCommand(this);
        if (undoCommand.getName().equalsIgnoreCase(commandArgs[0])) {
            undoCommand.performAction(commandArgs);
            return;
        }

        System.out.println("Invalid command. Try again.");
    }

    private void printNewLineForNewPage(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }

    public void startUp() {
        csvReader reader = new csvReader();
        reader.ingredientReader();
        // reader.test();
        SaveData.deserializeFromSave();
        pageData.loadUsersFromHistory();
        pageData.loadTeam();
    }

    public void runPage() {
        while (this.scanner != null) {
            this.currentPage.printContent();
            this.currentPage.printCommand();
            printGlobalCommand();

            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();
            
            try {
                if (input.equalsIgnoreCase("Exit")) {
                    executeCommand(input);
                    break;
                }
                executeCommand(input);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

            System.out.print("\nPress Enter to continue...");
            getScannerInput();

            printNewLineForNewPage(10);
        }
    }

    private Page createPages() {
        Page mainPage = new MainPage(pageData);
        Page userSetupPage = new UserSetupPage(pageData);
        Page userDashboardPage = new UserDashboardPage(pageData);
        Page userGuestPage = new GuestPage(pageData);
        Page foodPage = new FoodPage(pageData);
        Page historyPage = new HistoryPage(pageData);
        Page workoutPage = new WorkoutPage(pageData);
        Page shoppingListPage = new ShoppingListPage(pageData);
        Page teamPage = new TeamPage(pageData);

        // Set up page hierarchy
        mainPage.setChildrenPage(List.of(userSetupPage, userDashboardPage, userGuestPage));
        userSetupPage.setParentPage(mainPage);
        userDashboardPage.setParentPage(mainPage);
        userGuestPage.setParentPage(mainPage);

        userSetupPage.setChildrenPage(List.of(userDashboardPage));
        
        userDashboardPage.setChildrenPage(List.of(foodPage, historyPage, workoutPage, shoppingListPage, teamPage));
        foodPage.setParentPage(userDashboardPage);
        historyPage.setParentPage(userDashboardPage);
        workoutPage.setParentPage(userDashboardPage);
        shoppingListPage.setParentPage(userDashboardPage);
        teamPage.setParentPage(userDashboardPage);

        return mainPage;
    }
}