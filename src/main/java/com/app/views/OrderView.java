package com.app.views;

import com.app.views.utils.ColorKeys;
import com.app.views.utils.ColorText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderView extends View {

  public void printReceipt(int orderID, LocalDateTime timeOfOrder, String[] orderLines, int totalPrice, LocalDateTime getExpectedDeliveryTime) {
    double formattedTotalPrice = formatPrice(totalPrice);
    System.out.printf("-----------------------------------------%n");
    System.out.printf("%-12s %28s%n", "Order #" + orderID, formatDate(timeOfOrder));
    System.out.printf("-----------------------------------------%n");

    for (String orderLine : orderLines) {
      String[] orderLineData = orderLine.split(";");
      System.out.printf("%-30s %10.2f%n", orderLineData[2] + "x " + orderLineData[1] + " (#" + orderLineData[0] + "):", formatPrice(Integer.parseInt(orderLineData[4])));
    }

    System.out.printf("%-30s %10s%n", "", "-------");
    System.out.printf("%30s %10.2f%n", "Subtotal:", formattedTotalPrice * 0.8);
    System.out.printf("%30s %10.2f%n", "Tax (25 %):", formattedTotalPrice * 0.2);
    System.out.printf("%-30s %10s%n", "", "-------");
    System.out.printf("%30s %10.2f%n", "Total:", formattedTotalPrice);
    System.out.printf("-----------------------------------------%n");
    ColorText deliveryTimeTextFormatted = new ColorText("Delivery time:", ColorKeys.BLUE);
    ColorText deliveryTimeFormatted = new ColorText(formatDate(getExpectedDeliveryTime), ColorKeys.BLUE_BOLD);
    System.out.printf("%-15s %37s%n", deliveryTimeTextFormatted, deliveryTimeFormatted);
    System.out.printf("-----------------------------------------%n%n");
  }

  String formatDate(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");

    return localDateTime.format(formatter);
  }

  double formatPrice(int price) {
    return price / 100.00;
  }
}
