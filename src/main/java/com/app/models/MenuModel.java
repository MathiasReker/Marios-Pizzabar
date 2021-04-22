package com.app.models;

import com.app.models.menuactions.MenuAction;

public class MenuModel {
  private final MenuAction[] MENU_ACTIONS;
  private final String MENU_HEADER;
  private final String LOAD_TEXT;

  public MenuModel(String menuHeader, MenuAction[] menuActions, String leadText) {
    this.MENU_ACTIONS = menuActions;
    this.MENU_HEADER = menuHeader;
    this.LOAD_TEXT = leadText;
  }

  public MenuAction getMenuItem(int index) {
    return MENU_ACTIONS[index];
  }

  public String getLeadText() {
    return LOAD_TEXT;
  }

  public String getMenuHeader() {
    return MENU_HEADER;
  }

  public String[] getMenuActionDescriptions() {
    String[] result = new String[MENU_ACTIONS.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = MENU_ACTIONS[i].getDescription();
    }

    return result;
  }
}
