package com.app.models;

public class OrderLineModel {
  private final ItemModel ITEM;
  private int qty;

  public OrderLineModel(int qty, ItemModel item) {
    setQty(qty);
    this.ITEM = item;
  }

  public int getSubTotal() {
    return qty * ITEM.getPrice();
  }

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    if (qty <= 0) {
      throw new IllegalArgumentException();
    }
    this.qty = qty;
  }

  public ItemModel getItem() {
    return ITEM;
  }

  public int getUnitPrice() {
    return ITEM.getPrice();
  }
}
