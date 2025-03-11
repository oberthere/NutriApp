package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageRunner;

public class GoUserCommand extends UserCommand {
    private PageRunner pageRunner;
    
    public GoUserCommand(PageRunner pageRunner) {
        super.nameString = "Go";
        super.helpString = "[field not implemented]";
    }

    @Override
    public String getHelp() {
        return "";
    }
}
