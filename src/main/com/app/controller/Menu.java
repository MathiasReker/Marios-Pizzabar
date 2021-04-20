package com.app.controller;

import com.app.model.MenuModel;
import com.app.model.menuItems.MenuItem;
import com.app.view.MenuView;

import java.util.Scanner;

public class Menu {
  private MenuModel menu;
  private Scanner scanner = new Scanner(System.in);
  private MenuView menuView;

  public Menu(String menuHeader, String leadText, MenuItem[] menuItems) {
    menu = new MenuModel(menuHeader, menuItems, leadText);
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

  public int readChoice() {

    int userInput;

    while (!scanner.hasNextInt()) {
      //System.out.print(leadText);
      scanner.hasNextInt();
      scanner.nextLine();
    }
    userInput = scanner.nextInt();
    scanner.nextLine();

    return userInput;
  }
}
