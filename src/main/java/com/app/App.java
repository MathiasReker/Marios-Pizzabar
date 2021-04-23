package com.app;

import com.app.controllers.MenuController;
import com.app.controllers.menuactions.*;

public class App {
  public void run() {
    MenuActions[] menu = {
        new CreateOrderMenuAction("Create order"),
        new ViewActiveOrderMenuAction("View active order"),
        new DeleteOrderMenuAction("Delete order"),
        new ViewStatisticsMenuAction("View statistics"),
        new ItemSubmenuMenuAction("Item management"),
        new ExitMenuAction("Exit"),
    };

    new MenuController("Main Menu", "Please input number: ", menu).run();
  }
}
