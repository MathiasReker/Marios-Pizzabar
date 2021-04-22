package com.app.controllers;

import com.app.models.MenuModel;
import com.app.models.menuactions.MenuAction;
import com.app.views.MenuView;

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
      int input = SCANNER.nextInt() - 1;
      MENU.getMenuItem(input).run();
      running = MENU.getMenuItem(input).isKeepRunning();
    }
    // TODO: Validate user input
  }
}