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

  public void ItemsToFile(ItemModel[] items){
    char separator = ';';
    String[] result = new String[items.length];
    for (int i = 0; i < result.length; i++){
      StringBuilder builder = new StringBuilder();
      builder.append(items[i].getId() + separator);
      builder.append(items[i].getItemName() + separator);
      builder.append(items[i].getItemDescription() + separator);
      builder.append(items[i].getPrice());
      result[i] = builder.toString();
    }
    itemFile.writeFile(result);
  }

}
