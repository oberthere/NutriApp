package edu.rit.swen262.ui.pages;

import edu.rit.swen262.food.Ingredient;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.RestockIngredientCommand;
import edu.rit.swen262.ui.commands.SelectShoppingListCommand;
import edu.rit.swen262.user.service.ShoppingListComponent;

public class ShoppingListPage extends Page {
    
    public ShoppingListPage(PageData pageData) {
        super(pageData);
        this.pageName = "ShoppingList Page";
        super.userCommands.add(new SelectShoppingListCommand(pageData));
        super.userCommands.add(new RestockIngredientCommand());
    }
        

    @Override
    public void printContent() {
        System.out.println("\nShopping List: ");

        ShoppingListComponent shoppingListService = pageData.getCurrentUser().getShoppingListService(); // Fetch shopping list service
        if (shoppingListService == null) {
            System.out.println("\tNo shopping list selected");
        } else {
            for (Ingredient i : shoppingListService.getShoppingList()) {
                System.out.println("\t- " + i.getName());
            }
        }
    }
}
