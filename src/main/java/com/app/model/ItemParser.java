package com.app.model;

import java.util.ArrayList;

public class ItemParser {
  private final FileHandler itemFile;

  public ItemParser(String path) {
    itemFile = new FileHandler(path);
  }

  public ItemModel[] getItemsFromFile() {
    ArrayList<String> itemStrings = itemFile.readFile();
    ItemModel[] results = new ItemModel[itemStrings.size()];

    for (int i = 0; i < itemStrings.size(); i++) {
      String[] splitValues = itemStrings.get(i).split(";");
      results[i] =
          new ItemModel(
              splitValues[0], splitValues[1], splitValues[2], Integer.parseInt(splitValues[3]));
    }

    return results;
  }

  public void saveItemsToFile(ItemModel[] items) {
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
