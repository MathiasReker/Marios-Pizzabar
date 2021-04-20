package com.app;

import com.app.controller.Menu;
import com.app.model.menuActions.*;

public class App {
  public static void main(String[] args) {
    createOrderMenuAction test2 = new createOrderMenuAction();
    exitMenuAction test3 = new exitMenuAction();
    MenuAction[] menu = {test2,test3};
    Menu test = new Menu("Hello", "Hello", menu);

    test.run();
  }
}
