package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModel {
  private LocalDateTime timeOfOrder = LocalDateTime.now();
  private ArrayList<OrderLineModel> orderLines = new ArrayList<OrderLineModel>();
  private int orderId;

  //adds 30 mins to localDateTime (could be some other amount than 30 mins, we could move it to constructer and promt user for input)
  private LocalDateTime expectedPickUpTime = timeOfOrder.plusMinutes(30);
  private int orderStatus;

  public OrderModel(){
  }

  //getter

  public LocalDateTime getTimeOfOrder() {
    return timeOfOrder;
  }

  public int getOrderNumber() {
    return orderId;
  }

  public ArrayList<OrderLineModel> getOrderLines() {
    return orderLines;
  }

  /**
   * Loops through arraylist and returns the total amount
   * @return
   */

  public double totalPrice(){
    double totalAmount = 0;
    for (OrderLineModel oM: orderLines
         ) {
      totalAmount += oM.lineAmount();
    }
    return totalAmount;
  }


}
