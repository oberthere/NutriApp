package edu.rit.swen262.ui.commands;

public abstract class UndoableCommand extends UserCommand {

  public void undo() {
    throw new UnsupportedOperationException("Method not implemented");
  }
}
