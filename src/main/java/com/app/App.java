package com.app;

import com.app.controller.MenuController;
import com.app.model.menuactions.*;

public class App {
  public void run() {
    MenuAction[] menu = {
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
