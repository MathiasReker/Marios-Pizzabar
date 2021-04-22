package com.app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private final int orderStatus;
  private final ArrayList<OrderLineModel> orderLines;
  private final String orderId;
  int preparationTime = 30;
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(preparationTime);

  public OrderModel(String orderId, int orderStatus, ArrayList<OrderLineModel> orderLines) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
    this.orderLines = orderLines;
  }

  public OrderModel(LocalDateTime timeOfOrder, ArrayList<OrderLineModel> orderLines, String orderId, LocalDateTime expectedPickUpTime, int orderStatus) {
    this.timeOfOrder = timeOfOrder;
    this.orderLines = orderLines;
    this.orderId = orderId;
    this.expectedPickUpTime = expectedPickUpTime;
    this.orderStatus = orderStatus;
  }


  public LocalDateTime getTimeOfOrder() {
    return timeOfOrder;
  }

  public ArrayList<OrderLineModel> getOrderLines() {
    return orderLines;
  }

  public String getOrderId() {
    return orderId;
  }

  public LocalDateTime getExpectedPickUpTime() {
    return expectedPickUpTime;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  /**
   * Loops through arraylist and returns the total amount
   */
  public int totalPrice() {
    int totalAmount = 0;
    for (OrderLineModel o : orderLines
    ) {
      totalAmount += o.getSubTotal();
    }

    return totalAmount;
  }

}
