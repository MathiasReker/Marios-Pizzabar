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
    return leadText;
  }

  public String getMenuHeader() {
    return menuHeader;
  }

  public String[] getMenuActionDescriptions() {
    String[] result = new String[menuActions.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = menuActions[i].getDescription();
    }
    return result;
  }
}
