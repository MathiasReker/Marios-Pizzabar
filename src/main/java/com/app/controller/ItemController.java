package com.app.controller;

import com.app.model.ItemModel;
import com.app.model.ItemParser;
import com.app.view.ItemView;

import java.util.Scanner;

public class ItemController {
  private final ItemParser ITEM_PARSER =
      new ItemParser("data/itemdb/items2.txt"); //TODO swap to Path from config
  private final ItemView ITEM_VIEW = new ItemView();
  private ItemModel[] itemModels = ITEM_PARSER.getItemsFromFile();
  private Scanner scanner = new Scanner(System.in);

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


  public void deleteItem(){
    ITEM_VIEW.printTxt("Item to delete");
    int input = scanner.nextInt();

    itemModels = removeElement(input);

    ITEM_PARSER.saveItemsToFile(itemModels);
  }


  private ItemModel[] appendItem(ItemModel item){ //TODO Consider just using ArrayList
    ItemModel[] result = new ItemModel[itemModels.length+1];
    for(int i = 0; i < itemModels.length; i++){
      result[i] = itemModels[i];
    }
    result[itemModels.length] = item;

    return result;
  }

  private ItemModel[] removeElement(int index){ //TODO Consider just using ArrayList
    ItemModel[] result = new ItemModel[itemModels.length-1];
    int j = 0;
    for(int i = 0; i < itemModels.length; i++){
      if (index != i){
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
