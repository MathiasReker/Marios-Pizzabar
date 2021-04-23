package com.app.controllers.menuactions;

import com.app.controllers.MenuController;

public class OrderSubmenuMenuAction extends MenuActions {

  public OrderSubmenuMenuAction(String description) {
    super(description);
  }

  public void run() {
    MenuActions[] menu = {
        new YesMenuAction("Yes"),
        new NoMenuAction("No"),
    };

    new MenuController("Order line management", "Please input number: ", menu).run();
  }
}