package com.app.model;

import com.app.model.menuItems.MenuItem;

public class MenuModel {
  private MenuItem[] menuItems;
  private String menuHeader;
  private String leadText;

  public MenuModel(String menuHeader, MenuItem[] menuItems, String leadText){
    this.menuItems = menuItems;
    this.menuHeader = menuHeader;
    this.leadText = leadText;
  }

}
