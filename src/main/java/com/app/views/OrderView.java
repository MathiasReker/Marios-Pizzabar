package com.app.views;

import com.app.views.utils.ColorKeys;
import com.app.views.utils.ColorText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderView extends View {

  public void printReceipt(int orderID, LocalDateTime timeOfOrder, String[] orderLines, int totalPrice) {
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
    System.out.printf("-----------------------------------------%n%n");
  }

  String formatDate(LocalDateTime localDateTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss");

    return localDateTime.format(formatter);
  }

  double formatPrice(int price) {
    return price / 100.00;
  }

  public void printMenuOptions(String id, String item, String price, String[] menuAction, String[] menuItems, int[] itemPrice) {
    ColorText idHeader = new ColorText(id.toUpperCase(), ColorKeys.BLUE_BOLD_BRIGHT);
    ColorText itemHeader = new ColorText(item.toUpperCase(), ColorKeys.BLUE_BOLD_BRIGHT);
    ColorText priceHeader = new ColorText(price.toUpperCase(), ColorKeys.BLUE_BOLD_BRIGHT);
    System.out.printf("┌───────┬──────────────────────────────┬────────┐%n");
    System.out.printf("│ %-16s │ %-39s │ %-17s │%n", idHeader, itemHeader, priceHeader);
    System.out.printf("├───────┼──────────────────────────────┼────────┤%n");
    for (int i = 0; i < menuAction.length; i++) {
      ColorText menuActionFormatted = new ColorText(menuAction[i], ColorKeys.WHITE_BRIGHT);
      ColorText menuItemsFormatted = new ColorText(menuItems[i], ColorKeys.BLUE_BRIGHT);
      System.out.printf("│ %15s  │ %-39s │  %5.2f │%n", menuActionFormatted, menuItemsFormatted, formatPrice(itemPrice[i]));
    }
    System.out.printf("└───────┴──────────────────────────────┴────────┘%n");
  }
}
