package com.app.model.menuActions;

import com.app.controller.ItemController;

public class DeleteItemMenuAction extends MenuAction {

  public DeleteItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new ItemController().deleteItem();
  }
}
