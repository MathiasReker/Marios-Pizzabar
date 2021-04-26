package com.app.models;

public class ItemModel {
  private final String ID;
  private final String ITEM_NAME;
  private final String ITEM_DESCRIPTION;
  private final int PRICE;

  public ItemModel(String id, String itemName, String itemDescription, int price) {
    this.ID = id;
    this.ITEM_NAME = itemName;
    this.ITEM_DESCRIPTION = itemDescription;
    this.PRICE = price;
  }

  public String getId() {
    return ID;
  }

  public String getItemName() {
    return ITEM_NAME;
  }

  public String getItemDescription() {
    return ITEM_DESCRIPTION;
  }

  public int getPrice() {
    return PRICE;
  }
}
