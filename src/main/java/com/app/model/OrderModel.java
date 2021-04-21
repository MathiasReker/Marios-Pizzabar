package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private ArrayList<OrderLineModel> orderLines = new ArrayList<OrderLineModel>();
  private String orderId;

  //adds 30 mins to localDateTime (could be some other amount than 30 mins, we could move it to constructer and promt user for input)
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(30);
  private int orderStatus;

  public OrderModel(String orderId, int orderStatus){
    this.orderId = orderId;
    this.orderStatus = orderStatus;

  }

  //getter

  public LocalDateTime getTimeOfOrder() {
    return timeOfOrder;
  }

  public String getOrderNumber() {
    return orderId;
  }

  public ArrayList<OrderLineModel> getOrderLines() {
    return orderLines;
  }

  /**
   * Loops through arraylist and returns the total amount
   * @return
   */

  public int totalPrice(){
    int totalAmount = 0;
    for (OrderLineModel oM: orderLines
         ) {
      totalAmount += oM.getSubTotal();
    }
    return totalAmount;
  }

  public void addOrderLine(OrderLineModel orderLineModel){
    orderLines.add(orderLineModel);

  }



}
