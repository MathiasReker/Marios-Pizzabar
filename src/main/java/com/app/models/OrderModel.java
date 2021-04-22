package com.app.models;

import com.app.models.services.ConfigService;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private ArrayList<OrderLineModel> orderLines;
  private String orderId;

  //adds 30 mins to localDateTime (could be some other amount than 30 mins, we could move it to constructer and promt user for input)
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(30);
  private final int orderStatus;

  public OrderModel(String orderId, int orderStatus, ArrayList<OrderLineModel> orderLines) {
    this.orderId = orderId;
    this.orderStatus = orderStatus;
    this.orderLines = orderLines;

  }
  public OrderModel(LocalDateTime timeOfOrder, ArrayList<OrderLineModel> orderLines, String orderId, LocalDateTime expectedPickUpTime, int orderStatus){
    this.timeOfOrder = timeOfOrder;
    this.orderLines = orderLines;
    this.orderId = orderId;
    this.expectedPickUpTime = expectedPickUpTime;
    this.orderStatus = orderStatus;

  }

  //getter

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
   *
   * @return
   */
  public int totalPrice() {
    int totalAmount = 0;
    for (OrderLineModel oM : orderLines
    ) {
      totalAmount += oM.getSubTotal();
    }
    return totalAmount;
  }

}
