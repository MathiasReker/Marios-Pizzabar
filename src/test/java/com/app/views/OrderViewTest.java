package com.app.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderViewTest {

  @Test
  public void testFormatPriceCorrect(){
    OrderView test = new OrderView();
    String input = "100";
    String expected = "1,00";

    Assertions.assertEquals(expected, test.formatPrice(input));
  }

  @Test
  public void testFormatPriceInvalid(){
    OrderView test = new OrderView();
    String input = "100";
    String expected = "10,00";

    Assertions.assertNotEquals(expected, test.formatPrice(input));
  }
}