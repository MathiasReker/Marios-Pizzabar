package com.app.model.menuActions;

public class exitMenuAction extends MenuAction {

  public exitMenuAction(){
    super(false);
  }

  @Override
  public void run() {
    System.out.println("Exit");
  }
}
