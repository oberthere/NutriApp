package edu.rit.swen262.ui.commands;

import edu.rit.swen262.food.PantryStock;
import edu.rit.swen262.food.Recipe;
import edu.rit.swen262.shoppingList.strag.strategy.ShopCriteraLowIngredientStrategy;
import edu.rit.swen262.shoppingList.strag.strategy.ShopCriteriaLowRecipeStrategy;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.user.components.ShoppingListComponent;

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
        if (commandArgs.length < 2 || commandArgs.length > 3) {
            System.out.println("Error: Invalid number of arguments. Usage: " + getHelp());
            return;
        }

        pageData.getCurrentUser().setShoppingListComponent(new ShoppingListComponent());
        ShoppingListComponent shoppingListComponent = pageData.getCurrentUser().getShoppingListComponent();
        
        if (commandArgs.length == 2 && commandArgs[1].equals("LowIngredient")) {
            shoppingListComponent.setCriteria(new ShopCriteraLowIngredientStrategy());
            shoppingListComponent.generateShoppingList(null);
            System.out.println("Succesfully selected the Shopping List for Low Ingredients");
        } else if (commandArgs.length == 3 && commandArgs[1].equals("Recipe")) {
            Recipe recipe = PantryStock.getRecipeRecord().get(commandArgs[2]);
            if (recipe == null) {
                System.out.println("Error: Recipe name not found. Usage: " + getHelp());
                return;
            }
            shoppingListComponent.setCriteria(new ShopCriteriaLowRecipeStrategy());
            shoppingListComponent.generateShoppingList(recipe);
            
            System.out.println("Succesfully selected the Shopping List for " + recipe.getName());
        } else {
            System.out.println("Error: Invalid ShoppingList Selection: Check your arguments and try again. Usage: " + getHelp());
            return;
        }
        pageData.getCurrentUser().setShoppingListComponent(shoppingListComponent);
    }
}