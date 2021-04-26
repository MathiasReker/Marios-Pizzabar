package com.app.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private final ArrayList<OrderLineModel> ORDER_LINES;
  private final int ORDER_ID;
  private final int PREPARATION_TIME = 30;
  private OrderStatusKeys orderStatus;
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(PREPARATION_TIME);

  public OrderModel(int orderId, OrderStatusKeys orderStatus) {
    this.ORDER_ID = orderId;
    this.orderStatus = orderStatus;
    ORDER_LINES = new ArrayList<>();
  }

  public OrderModel(LocalDateTime timeOfOrder, ArrayList<OrderLineModel> orderLines, int orderId, LocalDateTime expectedPickUpTime, OrderStatusKeys orderStatus) {
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

  public int getOrderId() {
    return ORDER_ID;
  }

  public LocalDateTime getExpectedPickUpTime() {
    return expectedPickUpTime;
  }

  public OrderStatusKeys getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(OrderStatusKeys orderStatus) {
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

  public void addOrderLine(ItemModel item, int quantity) {
    ORDER_LINES.add(new OrderLineModel(quantity, item));
  }
}
