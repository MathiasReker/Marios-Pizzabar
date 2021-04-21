package com.app;

import com.app.controller.MenuController;
import com.app.model.menuActions.*;

public class App {
  public static void main(String[] args) {
    CreateOrderMenuAction createOrderMenuAction = new CreateOrderMenuAction("Create order");
    ViewActiveOrderMenuAction viewActiveOrderMenuAction = new ViewActiveOrderMenuAction("View active order");
    DeleteOrderMenuAction deleteOrderMenuAction = new DeleteOrderMenuAction("Delete order");
    ViewStatisticsMenuAction viewStatisticsMenuAction = new ViewStatisticsMenuAction("View statistics");

    ItemSubmenuMenuAction itemManagement = new ItemSubmenuMenuAction("Item management");

    ExitMenuAction exitMenuAction = new ExitMenuAction();
    MenuAction[] menu = {
        createOrderMenuAction,
        viewActiveOrderMenuAction,
        deleteOrderMenuAction,
        viewStatisticsMenuAction,
        itemManagement,
        exitMenuAction,
    };

    MenuController menuController = new MenuController("Main Menu", "Please input number: ", menu);

    menuController.run();
  }
}
