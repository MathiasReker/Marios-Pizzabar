package com.app.models;

public class ITEM_MODEL {
  private final String id;
  private final String itemName;
  private final String itemDescription;
  int price;

  public ITEM_MODEL(String id, String itemName, String itemDescription, int price) {
    this.id = id;
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.price = price;
  }


  public String getId() {
    return id;
  }

  public String getItemName() {
    return itemName;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public int getPrice() {
    return price;
  }
}
