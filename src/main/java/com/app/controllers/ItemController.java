package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.views.ItemView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemController {
  private final ItemView ITEM_VIEW = new ItemView();

  String path;
  private ItemModel[] itemModels;

  {
    try {
      path = new ConfigService("itemDb").getPath();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.print("File does not exists.");
    }
  }

  private final ItemService ITEM_PARSER = new ItemService(path);

  {
    try {
      itemModels = ITEM_PARSER.getItemsFromFile();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.print("File does not exists.");
    }
  }

  public void createItem(Scanner scanner) {
    ITEM_VIEW.print("Input ID");
    String id = scanner.nextLine();

    ITEM_VIEW.print("Input Item Name");
    String itemName = scanner.nextLine();

    ITEM_VIEW.print("Input Item description");
    String itemDescription = scanner.nextLine();

    ITEM_VIEW.print("Input price");
    int price = scanner.nextInt(); // TODO: Add validation

    ItemModel newItem = new ItemModel(id, itemName, itemDescription, price);

    itemModels = appendItem(newItem);
    ITEM_PARSER.saveItemsToFile(itemModels);
  }

  public void deleteItem(Scanner in) {
    ITEM_VIEW.print("Item to delete");
    int input = in.nextInt(); // TODO: Add Validation

    itemModels = removeElement(input);

    ITEM_PARSER.saveItemsToFile(itemModels);
  }

  ItemModel[] appendItem(ItemModel item) {
    ItemModel[] result = new ItemModel[itemModels.length + 1];
    System.arraycopy(itemModels, 0, result, 0, itemModels.length);
    result[itemModels.length] = item;

    return result;
  }

  ItemModel[] removeElement(int index) {
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

  public ItemModel lookupItem(String itemId) {
    for (ItemModel itemModel : itemModels) {
      if (itemId.equals(itemModel.getId())) {
        return itemModel;
      }
    }

    return null;
  }
}
