package com.app.controller;

import com.app.model.MenuModel;
import com.app.model.menuActions.MenuAction;
import com.app.view.MenuView;

import java.util.Scanner;

public class MenuController {
  private MenuModel menu;
  private Scanner scanner = new Scanner(System.in);
  private MenuView menuView;

  public MenuController(String menuHeader, String leadText, MenuAction[] menuActions) {
    menu = new MenuModel(menuHeader, menuActions, leadText);
    menuView = new MenuView();
  }

  // methods

  public void run() {
    boolean running = true;
    while (running) {
      menuView.printMenuText(menu.getLeadText());
      while (!scanner.hasNextInt()) {
        menuView.tryAgain();
        scanner.nextLine();
      }
      int input = scanner.nextInt();
      menu.getMenuItem(input).run();
      running = menu.getMenuItem(input).isKeepRunning();
    }
    // validering af input

  }
}
