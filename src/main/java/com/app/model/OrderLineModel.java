package com.app.model;

public class OrderLineModel {

  int qty;
  String itemId;
  int unitPrice;

  public OrderLineModel(int qty, String itemId, int unitPrice){
    this.qty = qty;
    this.itemId = itemId;
    this.unitPrice = unitPrice;
  }

  public int getSubTotal(){
    return qty * unitPrice;
  }

  public int getQty() {
    return qty;
  }

  public String getItemId() {
    return itemId;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  //den skal væk
  public String orderLineToString(){
    return "@"+qty + "@" + itemId + "@" + unitPrice;
  }
}
