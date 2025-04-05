package edu.rit.swen262.ui.commands;

public abstract class UserCommand {
    protected String nameString;
    protected String helpString;
    protected boolean active = true;

    public String getName() {return this.nameString;}
    public String getHelp() {return this.helpString;}
    public boolean isActive() {return this.active;}
    public void setActive(boolean bool) {this.active = bool;}
    public void performAction(String[] commandArgs) throws Exception {throw new UnsupportedOperationException("Method not implemented");}

    @Override
    public String toString() {
        return helpString;
    }}