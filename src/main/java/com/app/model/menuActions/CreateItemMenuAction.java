package com.app.model.menuActions;

public class CreateItemMenuAction extends MenuAction {

  public CreateItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    System.out.println("TODO: Create new item");
  }
}

