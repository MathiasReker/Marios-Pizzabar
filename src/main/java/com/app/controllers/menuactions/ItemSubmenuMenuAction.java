package com.app.controllers.menuactions;

import com.app.controllers.MenuController;

public class ItemSubmenuMenuAction extends MenuActions {

  public ItemSubmenuMenuAction(String description) {
    super(description);
  }

  public void run() {
    MenuActions[] menu = {
        new CreateItemMenuAction("Create item"),
        new DeleteItemMenuAction("Delete item"),
        new ExitMenuAction("Back"),
    };

    new MenuController("Menu management", "Please choose a menu option: ", menu).run();
  }
}
