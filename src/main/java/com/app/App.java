package com.app;

import com.app.controller.MenuController;
import com.app.model.*;
import com.app.model.menuActions.*;

public class App {
  public static void main(String[] args) {
    exitMenuAction test3 = new exitMenuAction();
    MenuAction[] menu = {test3};
    MenuController test = new MenuController("Hello", "Hello", menu);


    //test.run();
  }
}
