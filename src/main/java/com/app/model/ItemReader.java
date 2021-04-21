package com.app.model;

import java.util.ArrayList;

public class ItemReader {
  private final String PATH;
  private FileHandler itemFile;

  public ItemReader(String path) {
    PATH = path;
    itemFile = new FileHandler(PATH);
  }

  public ItemModel[] readItems() {
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
}
