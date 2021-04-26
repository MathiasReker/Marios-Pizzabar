package com.app.views;

import com.app.views.utils.ColorKeys;
import com.app.views.utils.ColorText;

public class ItemView extends View {
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

  double formatPrice(int price) {
    return price / 100.00;
  }
}
