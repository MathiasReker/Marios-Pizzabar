package com.app.view;

import com.app.model.OrderModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OrderView {

  public void printReceipt(OrderModel order){

    DateTimeFormatter dtmf = DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm");
    LocalDateTime timeOfOrder = order.getTimeOfOrder();

    System.out.println("******Order Number: " + order.getOrderNumber() +" ******" );
    System.out.println();
    System.out.println("Order Recieved: " + timeOfOrder);
    for (int i = 0; i < order.getOrderLines().size(); i++) {

    }

    System.out.println("Total price: "+order.totalPrice());

  }

}
