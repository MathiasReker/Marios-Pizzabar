package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class OrderControllerTest {
  @Test
  public void testItem() {
    OrderController test = new OrderController();
    String expected = "1";
    ItemModel testItemModel = test.item(expected);

    Assertions.assertEquals(expected, testItemModel.getId());
  }

  @Test
  public void testGenerateOrderId() {
    OrderController test = new OrderController();
    try {
      OrderService orderModels = new OrderService();
      Assertions.assertEquals(
          "O" + (orderModels.getOrdersFromFile().size() + 1), test.generateOrderId());
    } catch (FileNotFoundException e) {
    }
  }
}
