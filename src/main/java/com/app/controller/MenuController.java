package com.app.controller;

import com.app.model.MenuModel;
import com.app.model.menuActions.MenuAction;
import com.app.view.MenuView;

import java.util.Scanner;

public class MenuController {
  private final MenuModel MENU;
  private final Scanner SCANNER = new Scanner(System.in);
  private final MenuView MENU_VIEW;

  public MenuController(String menuHeader, String leadText, MenuAction[] menuActions) {
    MENU = new MenuModel(menuHeader, menuActions, leadText);
    MENU_VIEW = new MenuView();
  }

  // methods

  public void run() {
    boolean running = true;
    while (running) {
      MENU_VIEW.printMenuHeader(MENU.getMenuHeader());
      MENU_VIEW.printMenuOptions(MENU.getMenuActionDescriptions());
      MENU_VIEW.printMenuText(MENU.getLeadText());
      while (!SCANNER.hasNextInt()) {
        MENU_VIEW.tryAgain();
        SCANNER.nextLine();
      }
      int input = SCANNER.nextInt();
      MENU.getMenuItem(input).run();
      running = MENU.getMenuItem(input).isKeepRunning();
    }
    // validering af input

  }
}

/*
  - Main menu
    - Create order
    - View active orders
    - Delete order
    - View statistics
    - Item management
      - Create item
      - Delete item
      - Exit
    - Exit
 */