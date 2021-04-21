package com.app.model.menuActions;

import com.app.controller.MenuController;

public class ItemSubmenuMenuAction extends MenuAction {

  public ItemSubmenuMenuAction(String description) {
    super(description);
  }

  public void run() {
    CreateItemMenuAction createItemMenuAction = new CreateItemMenuAction("Create item");
    DeleteItemMenuAction deleteItemMenuAction = new DeleteItemMenuAction("Delete item");

    ExitMenuAction exitMenuAction = new ExitMenuAction("Exit");

    MenuAction[] menu = {createItemMenuAction, deleteItemMenuAction, exitMenuAction};

    MenuController menuController = new MenuController("Menu management", "Please input number: ", menu);

    menuController.run();
  }
}
