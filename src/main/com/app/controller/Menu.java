package com.app.controller;

import com.app.model.MenuModel;
import com.app.model.menuItems.*;
import java.util.Scanner;

public class Menu {

  private String menuHeader;
  private String leadText;
  private MenuModel menu;
  private Scanner scanner = new Scanner(System.in);

  public Menu(String menuHeader, String leadText, MenuItem[] menuItems){
    menu = new MenuModel(menuHeader,menuItems,leadText);
    view = new MenuView();
  }

  // methods

  public void run(){
    boolean running = true;
    while(running){
      String input = getinput();
      valid = validateInput(input);
      reactOnInput(input);
      //validering af input
      //menu.GetMenuItem(input).run();

    }
  }

  public int readChoice(){

    int userInput;

    while (!scanner.hasNextInt()){
      System.out.print(leadText);
      scanner.hasNextInt();
      scanner.nextLine();
    }
    userInput = scanner.nextInt();
    scanner.nextLine();

    return userInput;
  }
}
