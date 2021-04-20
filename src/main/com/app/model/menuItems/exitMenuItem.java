package com.app.model.menuItems;

public class exitMenuItem extends MenuItem{

  @Override
  public boolean run() {
    System.out.println("Exit");
    return false;
  }
}
