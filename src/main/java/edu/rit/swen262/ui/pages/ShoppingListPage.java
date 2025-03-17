package edu.rit.swen262.ui.pages;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.SelectShoppingListCommand;
import edu.rit.swen262.user.service.ShoppingListService;

public class ShoppingListPage extends Page {
    
    public ShoppingListPage(PageData pageData) {
        super(pageData);
        this.pageName = "ShoppingList";
        super.userCommands.add(new SelectShoppingListCommand(pageData));
    }
        

    @Override
    public void printContent() {
        System.out.println("\nShopping List");

        ShoppingListService shoppingListService = pageData.getCurrentUser().getShoppingListService(); // Fetch shopping list service
        if (shoppingListService == null) {
            System.out.println("\tNo shopping list selected");
        } else {
            for (Ingredient i : shoppingListService.getShoppingList()) {
                System.out.println("\t" + i.getName());
            }
        }
    }
}
