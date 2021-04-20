package com.app;

import com.app.controller.Menu;
import com.app.model.menuItems.*;

public class App {
  public static void main(String[] args) {
    createOrderMenuItem test2 = new createOrderMenuItem();
    exitMenuItem test3 = new exitMenuItem();
    MenuItem[] menu = {test2,test3};
    Menu test = new Menu("Hello", "Hello", menu);

    test.run();
  }
}
