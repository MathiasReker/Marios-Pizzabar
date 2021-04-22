package com.app.models.menuactions;

import com.app.controllers.ItemController;

import java.util.Scanner;

public class DeleteItemMenuAction extends MenuAction {

  public DeleteItemMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new ItemController().deleteItem(new Scanner(System.in));
  }
}
