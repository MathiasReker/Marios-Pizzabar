package com.app.models.services;

import com.app.models.ITEM_MODEL;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItemService {
  private final String PATH;
  private final FileService itemFile;

  public ItemService(String path) {
    PATH = path;
    itemFile = new FileService(PATH);
  }

  public ITEM_MODEL[] getItemsFromFile() throws FileNotFoundException {
    ArrayList<String> itemStrings = itemFile.readFile();
    ITEM_MODEL[] results = new ITEM_MODEL[itemStrings.size()];

    for (int i = 0; i < itemStrings.size(); i++) {
      String[] splitValues = itemStrings.get(i).split(";");
      results[i] =
          new ITEM_MODEL(
              splitValues[0], splitValues[1], splitValues[2], Integer.parseInt(splitValues[3]));
    }

    return results;
  }

  public void saveItemsToFile(ITEM_MODEL[] items) {
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
