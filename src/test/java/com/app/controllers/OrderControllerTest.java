package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class OrderControllerTest {
  @Test
  void item() {
    OrderController test = new OrderController();
    Assertions.assertEquals(ItemModel.class, test.item("1").getClass());
  }

  @Test
  void generateOrderId() {
    OrderController test = new OrderController();
    try {
      OrderService orderModels = new OrderService();
      Assertions.assertEquals(
          "O" + (orderModels.getOrdersFromFile().size() + 1), test.generateOrderId());
    } catch (FileNotFoundException e) {
    }
  }
}
