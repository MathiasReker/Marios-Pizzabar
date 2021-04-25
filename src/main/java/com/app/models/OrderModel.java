package com.app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private final ArrayList<OrderLineModel> ORDER_LINES;
  private final String ORDER_ID;
  private OrderStatusKeys orderStatus;
  int preparationTime = 30;
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(preparationTime);

  public OrderModel(String orderId, OrderStatusKeys orderStatus, ArrayList<OrderLineModel> orderLines) {
    this.ORDER_ID = orderId;
    this.orderStatus = orderStatus;
    this.ORDER_LINES = orderLines;
  }

  public OrderModel(LocalDateTime timeOfOrder, ArrayList<OrderLineModel> orderLines, String orderId, LocalDateTime expectedPickUpTime, OrderStatusKeys orderStatus) {
    this.timeOfOrder = timeOfOrder;
    this.ORDER_LINES = orderLines;
    this.ORDER_ID = orderId;
    this.expectedPickUpTime = expectedPickUpTime;
    this.orderStatus = orderStatus;
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

  public OrderStatusKeys getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatusKeys orderStatus){
    this.orderStatus = orderStatus;
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
