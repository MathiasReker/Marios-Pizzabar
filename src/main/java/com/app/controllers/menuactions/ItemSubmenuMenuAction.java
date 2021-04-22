package com.app.controllers.menuactions;

import com.app.controllers.MenuController;

public class ItemSubmenuMenuAction extends MenuAction {

  public ItemSubmenuMenuAction(String description) {
    super(description);
  }

  public void run() {
    MenuAction[] menu = {
        new CreateItemMenuAction("Create item"),
        new DeleteItemMenuAction("Delete item"),
        new ExitMenuAction("Back"),
    };

    new MenuController("Menu management", "Please input number: ", menu).run();
  }
}
