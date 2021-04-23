package com.app.controllers.menuactions;

import com.app.controllers.MenuController;

public class OrderViewSubmenuMenuAction extends MenuActions {

  public OrderViewSubmenuMenuAction(String description) {
    super(description);
  }

  public void run() {
    MenuActions[] menu = {
        new ViewActiveOrderMenuAction("View active orders"),
        new CompleteOrderMenuAction("Complete order"),
        new ExitMenuAction("Back"),
    };

    new MenuController("Menu management", "Please input number: ", menu).run();
  }
}
