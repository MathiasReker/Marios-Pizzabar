package com.app.views;

public class MenuView extends View {
  public void printMenuHeader(String header) {
    System.out.println("\n=== " + header + " ===");
  }

  public void printMenuOptions(String[] menuAction) {
    for (int i = 0; i < menuAction.length; i++) {
      System.out.println(i + 1 + "\t" + menuAction[i]);
    }
  }
}
