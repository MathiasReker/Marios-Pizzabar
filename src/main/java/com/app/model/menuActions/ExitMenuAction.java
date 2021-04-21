package com.app.model.menuActions;

public class ExitMenuAction extends MenuAction {

  public ExitMenuAction(){
    super(false, "Exit");
  }

  @Override
  public void run() {
    System.out.println("Exit");
  }
}
