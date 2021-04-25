package com.app.views;

import com.app.views.utils.ColorKeys;
import com.app.views.utils.ColorText;

abstract class View {
  public void printInline(String text) {
    System.out.print(new ColorText(text, ColorKeys.WHITE_BRIGHT));
  }

  public void print(String text) {
    System.out.println(new ColorText(text, ColorKeys.WHITE_BRIGHT));
  }

  public void print(double number) {
    System.out.printf("%.1f%n", number);
  }

  public void print() {
    System.out.println();
  }

  public void printInline(String text, ColorKeys color) {
    System.out.print(new ColorText(text, color));
  }

  public void printSuccess(String text) {
    System.out.println(new ColorText(text, ColorKeys.GREEN_BRIGHT));
  }

  public void printWarning(String text) {
    System.out.println(new ColorText(text, ColorKeys.RED_BRIGHT));
  }

  public void printInlineWarning(String text) {
    System.out.print(new ColorText(text, ColorKeys.RED));
  }
}
