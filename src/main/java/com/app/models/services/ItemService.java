package com.app.models.services;

import com.app.models.ItemModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ItemService {
  private final FileService FILE_SERVICE;

  public ItemService(String path) throws IOException {
    FILE_SERVICE = new FileService(path);
  }

  public ItemModel[] getItemsFromFile() throws FileNotFoundException {
    ArrayList<String> itemStrings = FILE_SERVICE.readFile();
    ItemModel[] results = new ItemModel[itemStrings.size()];

    for (int i = 0; i < itemStrings.size(); i++) {
      String[] splitValues = itemStrings.get(i).split(";");
      results[i] =
          new ItemModel(
              splitValues[0],
              splitValues[1],
              splitValues[2],
              Integer.parseInt(splitValues[3])
          );
    }

    return results;
  }

  public void saveItemsToFile(ItemModel[] items) throws FileNotFoundException {
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

    FILE_SERVICE.writeFile(result);
  }
}
