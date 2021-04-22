package com.app.models;

import com.app.controllers.ItemController;

public class OrderLineModel {

  private int qty;
  private ItemModel item;

  public OrderLineModel(int qty, ItemModel item) {
  setQty(qty);
   this.item = item;
  }

  public int getSubTotal() {
    return qty * item.getPrice();
  }

  public int getQty() {
    return qty;
  }

  public ItemModel getItem() {
    return item;
  }

  public int getUnitPrice() {
    return item.getPrice();
  }

  public void setQty(int qty){
    if (qty <= 0){
      throw new IllegalArgumentException();
    }
    this.qty = qty;
  }


  //den skal væk
  public String getFormattedOrderLine() {
    return qty + "@" + item.getId() + "@";
  }

}
