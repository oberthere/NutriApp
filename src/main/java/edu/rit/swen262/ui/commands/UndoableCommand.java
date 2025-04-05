package edu.rit.swen262.ui.commands;

import java.util.Stack;

public abstract class UndoableCommand<T extends Object> extends UserCommand {

  private Stack<T> commandDataStack = new Stack<>();

  protected boolean isCommandDataEmpty() {
    return commandDataStack.isEmpty();
  }

  protected Stack<T> getCommandDataStack() {
    return commandDataStack;
  }

  protected T popLastCommandData() {
    return commandDataStack.pop();
  }

  protected void addNextCommandDataToStack(T data) {
    commandDataStack.push(data);
  }

  public void undo() throws Exception {
    throw new UnsupportedOperationException("Method not implemented");
  }
}
