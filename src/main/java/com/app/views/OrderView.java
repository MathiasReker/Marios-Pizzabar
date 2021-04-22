package com.app.views;

import com.app.models.OrderModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class OrderView {

  public void printReceipt(OrderModel order) { //TODO Refactor to controller

    DateTimeFormatter dtmf = DateTimeFormatter.ofPattern("dd-MM-yyy hh:mm");
    LocalDateTime timeOfOrder = order.getTimeOfOrder();

    System.out.println("******Order Number: " + order.getOrderId() + " ******");
    System.out.println();
    System.out.println("Order Recieved: " + timeOfOrder);
    for (int i = 0; i < order.getOrderLines().size(); i++) {
      System.out.println(
          (i + 1) + ". \t" +
              "Pizza number: " + order.getOrderLines().get(i).getItem() + "\t" +
              "Quantity: " + order.getOrderLines().get(i).getQty() + "\t" +
              "Price: " + order.getOrderLines().get(i).getUnitPrice() + "\t" +
              "Sub total: " + order.getOrderLines().get(i).getSubTotal()
      );
    }
    System.out.println("Total price: " + order.totalPrice());
    System.out.println();
  }

  public void printReceipt(String orderID, LocalDateTime timeOfOrder, String[] orderLines, int totalPrice){
    System.out.printf("******Order Number: %s ******",orderID);
    System.out.println();
    System.out.printf("Order received: %s",timeOfOrder);
    for(String s : orderLines){
      System.out.println(s);
    }
    System.out.printf("Total price: ",totalPrice);
    System.out.println();
  }

  public void printTxt(String message){
    System.out.print(message);
  }
}
