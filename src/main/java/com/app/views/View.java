package com.app.views;

public class View {
  public void printInline(String s) {
    System.out.print(s);
  }

  public void print(String s) {
    System.out.println(s);
  }

  public void print(double text) {
    System.out.printf("%.1f%n", text);
  }
}
