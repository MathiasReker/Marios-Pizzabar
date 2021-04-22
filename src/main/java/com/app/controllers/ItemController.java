package com.app.controllers;

import com.app.models.ITEM_MODEL;
import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.views.ItemView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemController {
  private final ItemView ITEM_VIEW = new ItemView();
  private final ItemService ITEM_PARSER = new ItemService(null);
  String path;
  private ITEM_MODEL[] itemModels;

  {
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.print("File does not exists.");
    }
  }

  {
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.print("File does not exists.");
    }
  }

  //private final Scanner scanner = new Scanner(System.in);

  public void createItem(Scanner scanner) {
    ITEM_VIEW.print("Input ID");
    String id = scanner.nextLine();

    ITEM_VIEW.print("Input Item Name");
    String itemName = scanner.nextLine();

    ITEM_VIEW.print("Input Item description");
    String itemDescription = scanner.nextLine();

    ITEM_VIEW.print("Input price");
    int price = scanner.nextInt(); // TODO Add validation

    ITEM_MODEL newItem = new ITEM_MODEL(id, itemName, itemDescription, price);

    itemModels = appendItem(newItem);
    ITEM_PARSER.saveItemsToFile(itemModels);
  }


  public void deleteItem(Scanner in) {  // TODO: Mathias
    ITEM_VIEW.print("Item to delete");
    int input = in.nextInt(); //TODO add Validation

    itemModels = removeElement(input);

    ITEM_PARSER.saveItemsToFile(itemModels);
  }


  ITEM_MODEL[] appendItem(ITEM_MODEL item) { //TODO Consider just using ArrayList
    ITEM_MODEL[] result = new ITEM_MODEL[itemModels.length + 1];
    System.arraycopy(itemModels, 0, result, 0, itemModels.length);
    result[itemModels.length] = item;

    return result;
  }

  ITEM_MODEL[] removeElement(int index) { //TODO Consider just using ArrayList
    ITEM_MODEL[] result = new ITEM_MODEL[itemModels.length - 1];
    int j = 0;
    for (int i = 0; i < itemModels.length; i++) {
      if (index != i) {
        result[j] = itemModels[i];
        j++;
      }
    }
    return result;
  }

  public ITEM_MODEL[] getItemModels() {
    return itemModels;
  }
}
