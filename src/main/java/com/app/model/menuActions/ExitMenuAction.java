package com.app.model.menuActions;

public class ExitMenuAction extends MenuAction {

  public ExitMenuAction(String description) {
    super(false, description);
  }

  @Override
  public void run() {
    System.out.println("Exit"); // TODO: Move the print to view.
  }
}
