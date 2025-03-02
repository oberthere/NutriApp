package edu.rit.swen262.ui;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.pages.*;

@Component
public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page currentPage;
    private List<UserCommand> pageCommands;
    private final Scanner scanner;

    // Default Constructor (Used by Spring Boot)
    public PageRunner() {
        this.pageData = new PageData();
        this.mainPage = new MainPage(pageData);
        this.currentPage = this.mainPage;
        this.scanner = new Scanner(System.in);
    }

    public PageRunner(Page mainPage) {
        this.pageData = new PageData();
        this.mainPage = mainPage;
        this.currentPage = mainPage;
        this.scanner = new Scanner(System.in);
    }

    public PageRunner(PageData pageData, Page mainPage) {
        this.pageData = pageData;
        this.mainPage = mainPage;
        this.currentPage = mainPage;
        this.scanner = new Scanner(System.in);
    }

    public void startUp() {
        Page userSetupPage = new UserSetupPage(pageData);
        Page userDashboardPage = new UserDashboardPage(pageData);
        Page mealPage = new MealPage(pageData);
        Page historyPage = new HistoryPage(pageData);
        Page workoutPage = new WorkoutPage(pageData);

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

    public Page getCurrentPage() {
        return this.currentPage;
    }

    private void executeCommand(List<UserCommand> commands, String input) {
        String[] commandString = input.split(" ");
        for (UserCommand command : commands) {
            if (command.getName().equalsIgnoreCase(commandString[0])) {
                command.performAction(commandString);
                return;
            }
        }
        System.out.println("Invalid command. Try again.");
    }

    public void runPage() {
        while (true) {
            this.pageCommands = this.currentPage.getCommands();
            this.currentPage.printContent();
            this.currentPage.printCommand();

            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application...");
                break;
            }

            boolean navigated = false;
            for (Page child : currentPage.getChildrenPage()) {
                if (child.getPageName().equalsIgnoreCase(input)) {
                    currentPage = child;
                    navigated = true;
                    break;
                }
            }

            if (input.equalsIgnoreCase("back") && currentPage.getParentPage() != null) {
                currentPage = currentPage.getParentPage();
                navigated = true;
            }

            if (!navigated) {
                executeCommand(this.pageCommands, input);
            }
        }
        scanner.close();
    }
}
