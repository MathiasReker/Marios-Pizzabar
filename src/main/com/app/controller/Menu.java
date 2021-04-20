package com.app.controller;

import com.app.model.MenuModel;
import com.app.model.menuItems.MenuItem;

import java.util.Scanner;

public class Menu {
  private MenuModel menu;
  private Scanner scanner = new Scanner(System.in);

  public Menu(String menuHeader, String leadText, MenuItem[] menuItems) {
    menu = new MenuModel(menuHeader, menuItems, leadText);
    // view = new MenuView();
  }

  // methods

  public void run() {
    boolean running = true;
    while (running) {
      // view.printText(menu.getLeadText);
      while (!scanner.hasNextInt()) {
        // view.printTryAgain();
        scanner.nextLine();
      }
      int input = scanner.nextInt();
      menu.getMenuItem(input).run();
      running = menu.getMenuItem(input).isKeepRunning();
    }
    // validering af input
    // menu.GetMenuItem(input).run();
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
