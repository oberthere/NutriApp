package edu.rit.swen262.ui;

import org.springframework.stereotype.Component;
import java.util.Scanner;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.pages.*;
import java.util.ArrayList;
import java.util.List;

@Component  // This makes PageRunner a Spring-managed Bean
public class PageRunner {
    private PageData pageData;
    private Page mainPage;
    private Page mealPage;
    private Page historyPage;
    private Page userDashboardPage;
    private Page userSetupPage;
    private Page workoutPage;
    private Page currentPage;

    public PageRunner() {
        this.pageData = new PageData();
    }

    public void startUp() {
        mainPage = new MainPage(pageData);
        mealPage = new MealPage(pageData);
        historyPage = new HistoryPage(pageData);
        userDashboardPage = new UserDashboardPage(pageData);
        userSetupPage = new UserSetupPage(pageData);
        workoutPage = new WorkoutPage(pageData);

        List<Page> childPages = List.of(mealPage, historyPage, userDashboardPage, userSetupPage, workoutPage);
        mainPage.setChildrenPage(childPages);

        for (Page page : childPages) {
            page.setParentPage(mainPage);
        }

        currentPage = mainPage;
    }

    public Page getCurrentPage() {
        return this.currentPage;
    }

    public void setPage(Page page) {
        this.currentPage = page;
    }

    public void runPage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            currentPage.printContent();
            currentPage.printCommand();
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting application...");
                break;
            }

            boolean pageChanged = false;
            for (Page child : currentPage.getChildrenPage()) {
                if (child.getPageName().equalsIgnoreCase(input)) {
                    setPage(child);
                    pageChanged = true;
                    break;
                }
            }

            if (input.equalsIgnoreCase("back") && currentPage.getParentPage() != null) {
                setPage(currentPage.getParentPage());
                pageChanged = true;
            }

            if (!pageChanged) {
                executeCommand(currentPage.getCommands(), input);
            }
        }

        scanner.close();
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
}

