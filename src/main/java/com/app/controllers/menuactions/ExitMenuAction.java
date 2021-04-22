package com.app.controllers.menuactions;

import com.app.views.View;

public class ExitMenuAction extends MenuAction {

  public ExitMenuAction(String description) {
    super(false, description);
  }

  @Override
  public void run() {
    new View().print("Exit");
  }
}
