package com.app.views;

import com.app.views.utils.ColorKeys;
import com.app.views.utils.ColorText;

public class StatisticsView extends View {
  public void printStatistics(String key, double value) {
    ColorText keyFormatted = new ColorText(key.toUpperCase(), ColorKeys.GREEN_BRIGHT);
    System.out.printf("-----------------------------------------%n");
    System.out.printf(" %-40s %9.1f%n", keyFormatted, value);
    System.out.printf("-----------------------------------------%n");
  }

  public void printStatistics(String[] key, int[] value) {
    System.out.printf("-----------------------------------------%n");
    for (int i = 0; i < key.length; i++) {
      System.out.printf(" %-40s %9s%n", new ColorText(key[i], ColorKeys.CYAN_BRIGHT), value[i]);
      System.out.printf("-----------------------------------------%n");
    }
  }

  public void printHeader(String text) {
    System.out.printf("-----------------------------------------%n");
    System.out.printf(" %-49s %n", new ColorText(text.toUpperCase(), ColorKeys.GREEN_BRIGHT));
    System.out.printf("-----------------------------------------%n");
  }
}
