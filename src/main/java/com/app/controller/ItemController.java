package com.app.controller;

import com.app.model.ItemModel;
import com.app.model.ItemParser;
import com.app.view.ItemView;

import java.util.Scanner;

public class ItemController {
  private final ItemParser ITEM_LOADER = new ItemParser("data/itemdb/items.txt"); //swap to Path from config
  private final ItemView ITEM_VIEW = new ItemView();
  private ItemModel[] itemModels = ITEM_LOADER.getItemsFromFile();
  private Scanner scanner = new Scanner(System.in);

  public void createItem(){
    ITEM_VIEW.printTxt("Input ID");
    String id = scanner.nextLine();

    ITEM_VIEW.printTxt("Input Item Name");
    String itemName = scanner.nextLine();

    ITEM_VIEW.printTxt("Input Item description");
    String itemDescription = scanner.nextLine();

    ITEM_VIEW.printTxt("Input price");
    int price = scanner.nextInt(); //TODO Add validation

    ItemModel newItem = new ItemModel(id,itemName,itemDescription, price);
        
    //ITEM_LOADER.saveItemsToFile();
  }
}
