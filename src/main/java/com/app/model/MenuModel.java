package com.app.model;

import com.app.model.menuActions.*;

public class MenuModel {
  private MenuAction[] menuActions;
  private String menuHeader;
  private String leadText;

  public MenuModel(String menuHeader, MenuAction[] menuActions, String leadText){
    this.menuActions = menuActions;
    this.menuHeader = menuHeader;
    this.leadText = leadText;
  }

  public MenuAction getMenuItem(int index){
    return menuActions[index];
  }
  public String getLeadText(){
    return this.leadText;
  }
}
