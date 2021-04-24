package com.app.views.utils;

public class ColorText {
  private final String TEXT;
  private final ColorLibrary COLOR;

  public ColorText(String text, ColorLibrary color) {
    this.TEXT = text;
    this.COLOR = color;
  }

  public ColorText(int number, ColorLibrary color) {
    this.TEXT = String.valueOf(number);
    this.COLOR = color;
  }

  @Override
  public String toString() {
    return COLOR + TEXT + ColorLibrary.RESET;
  }
}
