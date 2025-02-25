package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.PageRunner;
import edu.rit.swen262.user.User;

public abstract class UserCommand {
    private PageData pageData;
    private PageRunner pageRunner;

    public String getName() {throw new UnsupportedOperationException("Method not implemented");}
    public void performAction(User user) {throw new UnsupportedOperationException("Method not implemented");}
}
