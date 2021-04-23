package com.app.views;

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
}
