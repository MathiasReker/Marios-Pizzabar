package com.app.model.menuActions;

import com.app.controller.MenuController;

public class ItemSubmenuMenuAction extends MenuAction {

  public ItemSubmenuMenuAction(String description) {
    super(description);
  }

  public void run() {
    MenuAction[] menu = {
        new CreateItemMenuAction("Create item"),
        new DeleteItemMenuAction("Delete item"),
        new ExitMenuAction("Exit"),
    };

    new MenuController("Menu management", "Please input number: ", menu).run();
  }
}
