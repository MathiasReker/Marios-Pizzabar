package com.app.models;

public class OrderLineModel {
  private final ITEM_MODEL item;
  private int qty;

  public OrderLineModel(int qty, ITEM_MODEL item) {
    setQty(qty);
    this.item = item;
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

  public ITEM_MODEL getItem() {
    return item;
  }

  public int getUnitPrice() {
    return item.getPrice();
  }
}
