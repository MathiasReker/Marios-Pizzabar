package com.app.views;

import java.time.LocalDateTime;

public class OrderView extends View { // TODO: Extend View

  public void printReceipt(String orderID, LocalDateTime timeOfOrder, String[] orderLines, int totalPrice) {
    System.out.printf("****** Order Number: %s ******%n", orderID);
    System.out.printf("Order received: %s%n", timeOfOrder);

    for (String orderLine : orderLines) {
      String[] orderLineData = orderLine.split(";");
      System.out.printf("Pizza number: %s\t", orderLineData[0]);
      System.out.printf("Pizza name: %s\t", orderLineData[1]);
      System.out.printf("Price: %s\t", formatPrice(orderLineData[3]));
      System.out.printf("Quantity: %s\t", orderLineData[2]);
      System.out.printf("Subtotal: %s\t", orderLineData[4]);
      System.out.println();
    }

    System.out.printf("Total price: %s", totalPrice);
    System.out.println();
  }

  public void printTxt(String message) {
    System.out.print(message);
  }

  String formatPrice(String price) {
    String wholeNumber = String.valueOf(price);
    String decimals = wholeNumber.substring(wholeNumber.length() - 2);
    wholeNumber = wholeNumber.substring(0, decimals.length() - 1);

    return wholeNumber + "," + decimals;
  }
}
