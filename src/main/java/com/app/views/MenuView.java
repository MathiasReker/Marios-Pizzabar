package com.app.views;

import com.app.views.utils.ColorKeys;
import com.app.views.utils.ColorText;

public class MenuView extends View {
  public void printMenuOptions(String header, String[] menuAction) {
    ColorText headerFormatted = new ColorText(header.toUpperCase(), ColorKeys.BLUE_BOLD_BRIGHT);
    System.out.printf("┌─────────────────────────────┐%n");
    System.out.printf("│ %-38s │%n", headerFormatted);
    System.out.printf("├─────┬───────────────────────┤%n");
    for (int i = 0; i < menuAction.length; i++) {
      ColorText menuActionFormatted = new ColorText(menuAction[i], ColorKeys.BLUE_BRIGHT);
      ColorText numberFormatted = new ColorText(i + 1, ColorKeys.WHITE_BRIGHT);
      System.out.printf("│  %-13s │ %-32s │%n", numberFormatted, menuActionFormatted);
    }
    System.out.printf("└─────┴───────────────────────┘%n");
  }
}
