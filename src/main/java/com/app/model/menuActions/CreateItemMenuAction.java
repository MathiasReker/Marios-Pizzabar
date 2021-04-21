package com.app.model.menuActions;

import com.app.controller.ItemController;

public class CreateItemMenuAction extends MenuAction {

  public CreateItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new ItemController().createItem();
  }
}

