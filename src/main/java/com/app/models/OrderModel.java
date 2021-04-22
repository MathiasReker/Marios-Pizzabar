package com.app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private final int ORDER_STATUS;
  private final ArrayList<OrderLineModel> ORDER_LINES;
  private final String ORDER_ID;
  int preparationTime = 30;
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(preparationTime);

  public OrderModel(String orderId, int orderStatus, ArrayList<OrderLineModel> orderLines) {
    this.ORDER_ID = orderId;
    this.ORDER_STATUS = orderStatus;
    this.ORDER_LINES = orderLines;
  }

  public OrderModel(LocalDateTime timeOfOrder, ArrayList<OrderLineModel> orderLines, String orderId, LocalDateTime expectedPickUpTime, int orderStatus) {
    this.timeOfOrder = timeOfOrder;
    this.ORDER_LINES = orderLines;
    this.ORDER_ID = orderId;
    this.expectedPickUpTime = expectedPickUpTime;
    this.ORDER_STATUS = orderStatus;
  }

  public LocalDateTime getTimeOfOrder() {
    return timeOfOrder;
  }

  public ArrayList<OrderLineModel> getOrderLines() {
    return ORDER_LINES;
  }

  public String getOrderId() {
    return ORDER_ID;
  }

  public LocalDateTime getExpectedPickUpTime() {
    return expectedPickUpTime;
  }

  public int getOrderStatus() {
    return ORDER_STATUS;
  }

  /**
   * Loops through arraylist and returns the total amount
   */
  public int totalPrice() {
    int totalAmount = 0;
    for (OrderLineModel o : ORDER_LINES
    ) {
      totalAmount += o.getSubTotal();
    }

    return totalAmount;
  }
}
