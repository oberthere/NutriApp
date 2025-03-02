package edu.rit.swen262.ui;

import java.util.List;
import java.util.Scanner;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.pages.*;

public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page currentPage;
    private List<UserCommand> pageCommands;

    public PageRunner() {
        this.pageData = new PageData();
        this.mainPage = new MainPage(pageData);
        this.currentPage = this.mainPage;
    }

    public PageRunner(Page mainPage) {
        this.pageData = new PageData();
        this.mainPage = mainPage;
        this.currentPage = mainPage;
    }

    public PageRunner(PageData pageData, Page mainPage) {
        this.pageData = pageData;
        this.mainPage = mainPage;
        this.currentPage = mainPage;
    }

    public void startUp() {
        // Instantiate all pages
        Page userSetupPage = new UserSetupPage(pageData);
        Page userDashboardPage = new UserDashboardPage(pageData);
        Page mealPage = new MealPage(pageData);
        Page historyPage = new HistoryPage(pageData);
        Page workoutPage = new WorkoutPage(pageData);

        // Set up proper parent-child relationships
        mainPage.setChildrenPage(List.of(userSetupPage, userDashboardPage));
        userSetupPage.setParentPage(mainPage);
        userDashboardPage.setParentPage(mainPage);

        userDashboardPage.setChildrenPage(List.of(mealPage, historyPage, workoutPage));
        mealPage.setParentPage(userDashboardPage);
        historyPage.setParentPage(userDashboardPage);
        workoutPage.setParentPage(userDashboardPage);

        currentPage = mainPage;
    }

    public void setPage(Page page) {
        this.currentPage = page;
    }

    /**
     * Returns the current page.
     * @return current page object
     */
    public Page getCurrentPage() {
        return this.currentPage;
    }

    private void executeCommand(List<UserCommand> commands) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] commandString = input.split(" ");
        System.out.println(commandString);
        
        for (UserCommand command : commands) {
            if (command.getName().equals(commandString[0])) {
                System.out.println("Running cmd of " + command.getName());
                command.performAction(commandString);
                scanner.close();
                runPage();
                return;
            }
        }

        // If the input matches a child page name, navigate to that page
        for (Page child : currentPage.getChildrenPage()) {
            if (child.getPageName().equalsIgnoreCase(input)) {
                setPage(child);
                scanner.close();
                runPage();
                return;
            }
        }

        // If "back" is typed and a parent page exists, navigate back
        if (input.equalsIgnoreCase("back") && currentPage.getParentPage() != null) {
            setPage(currentPage.getParentPage());
            scanner.close();
            runPage();
            return;
        }

        System.out.println("Invalid command. Try again.");
        scanner.close();
        runPage();
    }

    public void runPage() {
        this.pageCommands = this.currentPage.getCommands();
        this.currentPage.printContent();
        this.currentPage.printCommand();
        this.executeCommand(this.pageCommands);
    }
}
