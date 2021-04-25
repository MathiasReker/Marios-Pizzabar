package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.views.ItemView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ItemController {
  private final ItemView ITEM_VIEW = new ItemView();
  ItemService ITEM_PARSER;
  private ItemModel[] itemModels;

  {
    try {
      String path = new ConfigService("itemDb").getPath();
      ITEM_PARSER = new ItemService(path);
      itemModels = ITEM_PARSER.getItemsFromFile();
    } catch (FileNotFoundException e) {
      ITEM_VIEW.print("File does not exists.");
    }
  }

  public void createItem(Scanner scanner) {
    ITEM_VIEW.printInline("Input ID: ");
    String id = scanner.nextLine();

    ITEM_VIEW.printInline("Input Item Name: ");
    String itemName = scanner.nextLine();

    ITEM_VIEW.printInline("Input Item description: ");
    String itemDescription = scanner.nextLine();

    ITEM_VIEW.printInline("Input price: ");
    int price = validateInteger(scanner); // TODO: Add validation (done)

    ItemModel newItem = new ItemModel(id, itemName, itemDescription, price);

    itemModels = appendItem(newItem);
    ITEM_PARSER.saveItemsToFile(itemModels);
  }

  private int validateInteger(Scanner in) {
    while (!in.hasNextInt()) {
      ITEM_VIEW.printInlineWarning("Not a valid number. Please try again: ");
      in.nextLine();
    }

    return in.nextInt();
  }

  public void deleteItem(Scanner in) {
    ITEM_VIEW.printInline("Item to delete: ");
    int input = validateInteger(in); // TODO: Add Validation (done)

    itemModels = removeElement(input);

    ITEM_PARSER.saveItemsToFile(itemModels);
  }

  ItemModel[] appendItem(ItemModel item) {
    ItemModel[] result = new ItemModel[itemModels.length + 1];
    System.arraycopy(itemModels, 0, result, 0, itemModels.length);
    result[itemModels.length] = item;

    return result;
  }

  private ItemModel[] removeElement(int index) {
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
