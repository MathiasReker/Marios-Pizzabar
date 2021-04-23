package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderControllerTest {
  /*
  @Test
  public void testItem() {
    OrderController test = new OrderController();
    String expected = "1";
    ItemModel testItemModel = test.item(expected);

    Assertions.assertEquals(expected, testItemModel.getId());
  }
  */

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

  @Test
  public void testLookupOrder() {
    OrderController test = new OrderController();
    ItemModel newItem = new ItemModel("1", "test", "test", 1);
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    OrderLineModel testLine = new OrderLineModel(1, newItem);
    ArrayList<OrderLineModel> lines = new ArrayList<>();
    lines.add(testLine);
    OrderModel testOrder = new OrderModel("test", 1, lines);
    orderModels.add(testOrder);

    OrderModel expected = testOrder;

    OrderModel actual = test.lookupOrder("test", orderModels);

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testLookupNull() {
    OrderController test = new OrderController();
    ItemModel newItem = new ItemModel("1", "test", "test", 1);
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    OrderLineModel testLine = new OrderLineModel(1, newItem);
    ArrayList<OrderLineModel> lines = new ArrayList<>();
    lines.add(testLine);
    OrderModel testOrder = new OrderModel("test", 1, lines);
    orderModels.add(testOrder);

    OrderModel expected = null;

    OrderModel actual = test.lookupOrder("this is a test", orderModels);

    Assertions.assertEquals(expected, actual);
  }


  @Test
  public void testCreateOrderLine(){

    String input = "\n1\n1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    OrderController test = new OrderController(new Scanner(System.in));

    OrderLineModel line = test.createOrderLine();


 }
}
