package com.app.model;

import com.app.model.services.FileService;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItemParser {
  private final String PATH;
  private final FileService itemFile;

  public ItemParser(String path) {
    PATH = path;
    itemFile = new FileService(PATH);
  }

  public ItemModel[] getItemsFromFile() throws FileNotFoundException {
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
