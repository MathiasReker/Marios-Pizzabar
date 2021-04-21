package com.app.model;

import java.util.ArrayList;

public class ItemParser {
  private final String PATH;
  private FileHandler itemFile;

  public ItemParser(String path) {
    PATH = path;
    itemFile = new FileHandler(PATH);
  }

  public ItemModel[] fileToItems() {
    ArrayList<String> itemStrings = itemFile.readFile();
    ItemModel[] items = new ItemModel[itemStrings.size()];

    for (int i = 0; i < itemStrings.size(); i++) {
      String[] splitValues = itemStrings.get(i).split(";");
      items[i] =
          new ItemModel(
              splitValues[0], splitValues[1], splitValues[2], Integer.parseInt(splitValues[3]));
    }
    return items;
  }

  public void ItemsToFile(ItemModel[] items) {
    String[] result = new String[items.length];
    for (int i = 0; i < result.length; i++) {
      result[i] =
          String.join(
              ";",
              items[i].getId(),
              items[i].getItemName(),
              items[i].getItemDescription(),
              String.valueOf(items[i].getPrice()));
    }
    itemFile.writeFile(result);
  }
}
