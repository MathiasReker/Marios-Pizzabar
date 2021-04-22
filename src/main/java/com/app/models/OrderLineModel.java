package com.app.models;

import com.app.controllers.ItemController;

public class OrderLineModel {

  private int qty;
  private ItemModel item;
  private ItemController itemController = new ItemController();


  public OrderLineModel(int qty, String id) {
    this.qty = qty;
    

  }

  public OrderLineModel(int qty, ItemModel item) {
    this.qty = qty;
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


  //den skal væk
  public String getFormattedOrderLine() {
    return qty + "@" + item.getId() + "@";
  }
}
