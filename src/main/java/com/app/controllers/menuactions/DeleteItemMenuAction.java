package com.app.controllers.menuactions;

import com.app.controllers.ItemController;

import java.util.Scanner;

public class DeleteItemMenuAction extends MenuActions {

  public DeleteItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    Scanner in = new Scanner(System.in);
    new ItemController().deleteItem(in);
  }
}
