package com.app.views;

import com.app.views.utils.ColorLibrary;
import com.app.views.utils.ColorText;

abstract class View {
  public void printInline(String text) {
    System.out.print(text);
  }

  public void print(String text) {
    System.out.println(text);
  }

  public void print(double number) {
    System.out.printf("%.1f%n", number);
  }

  public void print() {
    System.out.println();
  }

  public void print(String text, ColorLibrary color) {
    System.out.println(new ColorText(text, color));
  }
}
