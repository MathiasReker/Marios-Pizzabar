package com.app.views.utils;

public class ColorText {
  private final String TEXT;
  private final ColorKeys COLOR;

  public ColorText(double text, ColorKeys color) {
    this.TEXT = String.valueOf(text);
    this.COLOR = color;
  }

  public ColorText(String text, ColorKeys color) {
    this.TEXT = text;
    this.COLOR = color;
  }

  public ColorText(int number, ColorKeys color) {
    this.TEXT = String.valueOf(number);
    this.COLOR = color;
  }

  @Override
  public String toString() {
    return COLOR + TEXT + ColorKeys.RESET;
  }
}
