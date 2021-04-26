package com.app.models;

public class OrderLineModel {
  private ItemModel item;
  private int qty;

  public OrderLineModel(int qty, ItemModel item) throws IllegalArgumentException {
    setQty(qty);
    setItem(item);
  }

  public int getSubTotal() {
    return qty * item.getPrice();
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
    return item;
  }

  public void setItem(ItemModel item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }

    this.item = item;

  }

  public int getUnitPrice() {
    return item.getPrice();
  }
}
