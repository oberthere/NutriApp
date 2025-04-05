package edu.rit.swen262.ui.commands;

import edu.rit.swen262.ui.PageRunner;

public class UndoPreviousCommand extends UserCommand {
  private PageRunner pageRunner;

  public UndoPreviousCommand(PageRunner pageRunner) {
    this.nameString = "undo";
    this.helpString = "Undo";
    this.pageRunner = pageRunner;
  }

  @Override
  public void performAction(String[] commandArgs) throws Exception {
    if (pageRunner.isCommandHistoryEmpty()) {
      throw new Exception("No commands to undo.");
    }
    UndoableCommand<Object> lastRanCommand = pageRunner.popLastUndoableCommand();
    lastRanCommand.undo();
    // TODO Save to history?
  }
}
