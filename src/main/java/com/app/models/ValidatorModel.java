package com.app.models;

import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.views.OrderView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ValidatorModel {

  private final OrderView ORDER_VIEW = new OrderView();
  private final Scanner SCANNER = new Scanner(System.in);

  public int validInputInt() {

    int userInput;

    while (!SCANNER.hasNextInt()) {
      System.out.print("Not a valid input, please try again: ");
      SCANNER.next();
    }
    userInput = SCANNER.nextInt();
    SCANNER.nextLine();

    return userInput;
  }

  public String getValidId(String itemId) { // TODO: Never used?

    String newItemId = itemId;

    if (isValidId(itemId)) {
      return itemId;
    } else {
      while (!isValidId(newItemId)) {
        System.out.print("Not a Valid itemID, please try again: ");
        newItemId = SCANNER.nextLine();
      }

      return newItemId;
    }
  }

  public boolean isValidId(String itemId) {
    String path = null;
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }
    final ItemService ITEM_PARSER = new ItemService(path);
    ItemModel[] itemModels;
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
      for (ItemModel itemModel : itemModels) {
        if (itemId.equals(itemModel.getId())) {
          return true;
        }
      }
    } catch (FileNotFoundException e) {
      ORDER_VIEW.printInline("File does not exists.");
    }
    return false;
  }
}



