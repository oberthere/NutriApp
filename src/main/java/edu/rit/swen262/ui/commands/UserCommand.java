package edu.rit.swen262.ui.commands;

import edu.rit.swen262.user.User;

public abstract class UserCommand {
    protected String nameString;

    public String getName() {return this.nameString;}
    public void performAction(String[] commandArgs) {throw new UnsupportedOperationException("Method not implemented");}}
