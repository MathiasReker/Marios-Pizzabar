package com.app.models.services;

import com.app.models.ItemModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItemService {
  private final String PATH;
  private final FileService itemFile;

  public ItemService(String path) {
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
