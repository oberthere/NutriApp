package edu.rit.swen262.ui.commands;

import edu.rit.swen262.user.User;

public abstract class UserCommand {
    protected String name;

    public String getName() {return this.name;}
    public void performAction(User user) {throw new UnsupportedOperationException("Method not implemented");}
}
