package com.app.controller;

import com.app.model.services.ConfigService;
import com.app.model.ItemModel;
import com.app.model.services.ItemService;
import com.app.view.ItemView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemController {
  private final ItemView ITEM_VIEW = new ItemView();
  String path;

  {
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.printTxt("File does not exists.");
    }
  }

  private final ItemService ITEM_PARSER = new ItemService(path);
  private ItemModel[] itemModels;

  {
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.printTxt("File does not exists.");
    }
  }

  private final Scanner scanner = new Scanner(System.in);

  public void createItem() {
    ITEM_VIEW.printTxt("Input ID");
    String id = scanner.nextLine();

    ITEM_VIEW.printTxt("Input Item Name");
    String itemName = scanner.nextLine();

    ITEM_VIEW.printTxt("Input Item description");
    String itemDescription = scanner.nextLine();

    ITEM_VIEW.printTxt("Input price");
    int price = scanner.nextInt(); // TODO Add validation

    ItemModel newItem = new ItemModel(id, itemName, itemDescription, price);

    itemModels = appendItem(newItem);
    ITEM_PARSER.saveItemsToFile(itemModels);
  }


  public void deleteItem() {  // TODO: Mathias
    ITEM_VIEW.printTxt("Item to delete");
    int input = scanner.nextInt(); //TODO add Validtion

    itemModels = removeElement(input);

    ITEM_PARSER.saveItemsToFile(itemModels);
  }


  private ItemModel[] appendItem(ItemModel item) { //TODO Consider just using ArrayList
    ItemModel[] result = new ItemModel[itemModels.length + 1];
    for (int i = 0; i < itemModels.length; i++) {
      result[i] = itemModels[i];
    }
    result[itemModels.length] = item;

    return result;
  }

  private ItemModel[] removeElement(int index) { //TODO Consider just using ArrayList
    ItemModel[] result = new ItemModel[itemModels.length - 1];
    int j = 0;
    for (int i = 0; i < itemModels.length; i++) {
      if (index != i) {
        result[j] = itemModels[i];
        j++;
      }
    }
    return result;
  }

  public ItemModel[] getItemModels() {
    return itemModels;
  }
}
