package com.app.view;

import com.app.model.menuActions.MenuAction;

import java.awt.*;

public class MenuView {

  public void tryAgain(){
    System.out.print("Please try again");
  }

  public void printMenuText(String leadtext){
    System.out.print(leadtext);
  }

  public void printMenuHeader(String header) {
    System.out.println("\n==="+header+"===");
  }

  public void printMenuOptions(String[] menuAction) {
    for (int i = 0; i < menuAction.length; i++) {
      System.out.println(i + "\t" + menuAction[i]);
    }
  }

}
