package com.app.view;

public class MenuView {

  public void tryAgain() {
    System.out.print("Please try again: ");
  }

  public void printMenuText(String leadText) {
    System.out.print(leadText);
  }

  public void printMenuHeader(String header) {
    System.out.println("\n=== " + header + " ===");
  }

  public void printMenuOptions(String[] menuAction) {
    for (int i = 0; i < menuAction.length; i++) {
      System.out.println(i +1+ "\t" + menuAction[i]);
    }
  }
}
