package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.services.ConfigService;
import com.app.models.services.ItemService;
import com.app.views.ItemView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ItemController {
  private final ItemView ITEM_VIEW = new ItemView();
  private ItemService itemService;
  private ItemModel[] itemModels;

  {
    try {
      String path = new ConfigService("itemDb").getPath();
      itemService = new ItemService(path);
      itemModels = itemService.getItemsFromFile();
    } catch (IOException e) {
      ITEM_VIEW.printWarning(e.getMessage());
    }
  }

  public void createItem(Scanner scanner) {
    ITEM_VIEW.printInline("Input ID: ");
    String id = scanner.nextLine();

    if (lookupItem(id) != null) {
      ITEM_VIEW.printWarning("Item already exists");
    } else {

      ITEM_VIEW.printInline("Input Item Name: ");
      String itemName = scanner.nextLine();

      ITEM_VIEW.printInline("Input Item description: ");
      String itemDescription = scanner.nextLine();

      ITEM_VIEW.printInline("Input price: ");
      int price = validateInteger(scanner);

      ItemModel newItem = new ItemModel(id, itemName, itemDescription, price);
      itemModels = appendItem(newItem);
      try {
        itemService.saveItemsToFile(itemModels);
      } catch (FileNotFoundException e) {
        ITEM_VIEW.printWarning(e.getMessage());
      }
    }
  }

  private int validateInteger(Scanner in) {
    while (!in.hasNextInt()) {
      ITEM_VIEW.printInlineWarning("Not a valid number. Please try again: ");
      in.nextLine();
    }

    return in.nextInt();
  }

  private int validateIntegerRange(Scanner in, int max) {
    int result = validateInteger(in);

    while (result > max || result <= 0) {
      ITEM_VIEW.printInlineWarning("Not a valid menu choice. Please try again: ");
      in.nextLine();
      result = validateInteger(in);
    }

    return result;
  }

  public void deleteItem(Scanner in) {
    ITEM_VIEW.printInline("Item to delete: ");
    int input = validateIntegerRange(in, itemModels.length - 1);

    itemModels = removeElement(input);

    try {
      itemService.saveItemsToFile(itemModels);
    } catch (FileNotFoundException e) {
      ITEM_VIEW.printWarning(e.getMessage());
    }
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

    ITEM_VIEW.printSuccess("The item has been removed.");

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

  public void printItemMenu() {
    String[] result = new String[itemModels.length];

    String[] itemId = new String[itemModels.length];
    String[] itemName = new String[itemModels.length];
    int[] unitPrice = new int[itemModels.length];

    for (int i = 0; i < result.length; i++) {
      itemId[i] = itemModels[i].getId();
      itemName[i] = itemModels[i].getItemName();
      unitPrice[i] = itemModels[i].getPrice();
    }

    ITEM_VIEW.printMenuOptions("Id", "Item", "Price", itemId, itemName, unitPrice);
  }
}
