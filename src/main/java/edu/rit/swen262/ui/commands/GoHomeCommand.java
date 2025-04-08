package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageRunner;

public class GoHomeCommand extends UserCommand {

  PageRunner pageRunner;

  public GoHomeCommand(PageRunner pageRunner) {
    super.nameString = "ReturnHome";
    super.helpString = "ReturnHome";
    this.pageRunner = pageRunner;
  }

  @Override
  public void performAction(String[] commandArgs) throws Exception {
    this.pageRunner.setPage(this.pageRunner.getMainPage());
  }
}
