package com.app;

import com.app.controllers.MenuController;
import com.app.controllers.StartController;
import com.app.controllers.menuactions.*;

public class App {
  public void run() {
    new StartController();

    MenuActions[] menu = {
        new CreateOrderMenuAction("Create order"),
        new OrderViewSubmenuMenuAction("Order view"),
        new CancelOrderMenuAction("Cancel order"),
        new ViewStatisticsMenuAction("View statistics"),
        new ItemSubmenuMenuAction("Item management"),
        new ExitMenuAction("Exit"),
    };

    new MenuController("Main Menu", "Please input number: ", menu).run();
  }
}
