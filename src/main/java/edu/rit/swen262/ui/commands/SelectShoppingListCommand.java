package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.shoppingList.strag.strategy.ShopCriteraLowIngredientStrategy;
import edu.rit.swen262.shoppingList.strag.strategy.ShopCriteriaLowRecipeStrategy;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.service.ShoppingListService;

public class SelectShoppingListCommand extends UserCommand {
    private PageData pageData;

    public SelectShoppingListCommand(PageData pageData) {
        super.nameString = "Select";
        super.helpString = "Select [LowIngredient | Recipe [Recipe Name]]";
        this.pageData = pageData;
    }

    /**
     * Method to select and goes to an user page
     * @param commandArgs Select Shopping List [Low Ingredient] | Recipe [Recipe Name]
     */
    @Override
    public void performAction(String[] commandArgs) {
        
        // Ensure proper length
        if (commandArgs.length < 2) {
            System.out.println("Error: Please provide a shopping list type. Usage: Select [LowIngredient | Recipe [Recipe Name]]");
            return;
        }

        pageData.getCurrentUser().setShoppingListService(new ShoppingListService());
        ShoppingListService shoppingListService = pageData.getCurrentUser().getShoppingListService();

        if (commandArgs.length < 3) {
            shoppingListService.setCriteria(new ShopCriteraLowIngredientStrategy());
            shoppingListService.generateShoppingList(null);
        } else {
            Recipe recipe = PantryStock.getRecipeRecord().get(commandArgs[2]);
            shoppingListService.setCriteria(new ShopCriteriaLowRecipeStrategy());
            shoppingListService.generateShoppingList(recipe);
            System.out.println("Sucesfully selected the Shopping List for " + recipe.getName());
        }
        pageData.getCurrentUser().setShoppingListService(shoppingListService);
    }
}
