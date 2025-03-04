package edu.rit.swen262.ui.commands;

public abstract class UserCommand {
    protected String nameString;
    protected String helpString;

    public String getName() {return this.nameString;}
    public String getHelp() {return this.helpString;}
    public void performAction(String[] commandArgs) {throw new UnsupportedOperationException("Method not implemented");}


    @Override
    public String toString() {
        return helpString;
    }}

    