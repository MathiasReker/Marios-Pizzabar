package com.app.controller;

import java.util.Scanner;

public class Menu {

  private String menuHeader;
  private String leadText;
  private String [] menuItems;
  private Scanner scanner = new Scanner(System.in);

  public Menu(String menuHeader, String leadText, String [] menuItems){

    this.menuHeader = menuHeader;
    this.leadText = leadText;
    this.menuItems = menuItems;

  }

  // methods

  public void printMenu(){

    System.out.println(menuHeader);
    for (String s: menuItems){
      System.out.println(s);
    }
    System.out.print(leadText);

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
