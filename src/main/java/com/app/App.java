package com.app;

import com.app.controller.MenuController;
import com.app.model.menuActions.MenuAction;
import com.app.model.menuActions.ItemSubmenuMenuAction;
import com.app.model.menuActions.ExitMenuAction;

public class App {
  public static void main(String[] args) {
    ItemSubmenuMenuAction orderSubMenuAction = new ItemSubmenuMenuAction("Order menu");
    ExitMenuAction exitMenuAction = new ExitMenuAction();
    MenuAction[] menu = {orderSubMenuAction, exitMenuAction};
    MenuController menuController = new MenuController("Main Menu", "Please input number: ", menu);
    // oprette bestilling
    // håndtere bestillinger
    // i actions
    menuController.run();
  }
}
