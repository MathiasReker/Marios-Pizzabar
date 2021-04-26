package com.app.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderViewTest {

  @Test
  public void testFormatPriceCorrect() {
    OrderView test = new OrderView();
    String input = "100";
    double expected = 1;

    Assertions.assertEquals(expected, test.formatPrice(Integer.parseInt(input)));
  }

  @Test
  public void testFormatPriceInvalid() {
    OrderView test = new OrderView();
    String input = "100";
    double expected = 10;

    Assertions.assertNotEquals(expected, test.formatPrice(Integer.parseInt(input)));
  }
}
