package com.app.controllers.menuactions;

import com.app.controllers.ItemController;

import java.util.Scanner;

public class CreateItemMenuAction extends MenuActions {

  public CreateItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    Scanner in = new Scanner(System.in);
    new ItemController().createItem(in);
  }
}
