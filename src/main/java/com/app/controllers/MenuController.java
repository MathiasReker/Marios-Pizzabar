package com.app.controllers;

import com.app.controllers.menuactions.MenuActions;
import com.app.models.MenuModel;
import com.app.views.MenuView;

import java.util.Scanner;

public class MenuController {
  private final MenuModel MENU;
  private final Scanner SCANNER = new Scanner(System.in);
  private final MenuView MENU_VIEW;

  public MenuController(String menuHeader, String leadText, MenuActions[] menuActions) {
    MENU = new MenuModel(menuHeader, menuActions, leadText);
    MENU_VIEW = new MenuView();
  }

  public void run() {
    boolean running = true;
    while (running) {
      MENU_VIEW.printMenuOptions(MENU.getMenuHeader(), MENU.getMenuActionDescriptions());
      MENU_VIEW.printInline(MENU.getLeadText());
      int input = validateIntegerRange(SCANNER, MENU.getMenuActionDescriptions().length) - 1;
      MENU.getMenuItem(input).run();
      running = MENU.getMenuItem(input).isKeepRunning();
    }
  }

  private int validateInteger(Scanner in) {
    while (!in.hasNextInt()) {
      MENU_VIEW.printInlineWarning("Not a valid menu choice. Please try again: ");
      in.nextLine();
    }

    return in.nextInt();
  }

  private int validateIntegerRange(Scanner in, int max) {
    int result = validateInteger(in);

    while (result > max || result <= 0) {
      MENU_VIEW.printInlineWarning("Not a valid menu choice. Please try again: ");
      in.nextLine();
      result = validateInteger(in);
    }

    return result;
  }
}
