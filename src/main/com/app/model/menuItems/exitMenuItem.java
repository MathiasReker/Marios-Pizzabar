package com.app.model.menuItems;

public class exitMenuItem extends MenuItem{

  public exitMenuItem(){
    super(false);
  }

  @Override
  public void run() {
    System.out.println("Exit");
  }
}
