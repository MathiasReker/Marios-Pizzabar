package com.app.models.menuactions;

import com.app.controllers.ItemController;

public class CreateItemMenuAction extends MenuAction {

  public CreateItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new ItemController().createItem();
  }
}

