package com.app.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderModelTest {


  @Test
  public void testGetValidOrderStatus() {

    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals(0, orderModel.getOrderStatus());
  }

  @Test
  public void testGetInValidOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertNotEquals(1, orderModel.getOrderStatus());
  }

  @Test
  public void testGetValidTotalPrice() {
    ItemModel test = new ItemModel("test", "test", "test", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, test);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals(200, orderModel.totalPrice());
  }

  @Test
  public void testGetInvalidTotalPrice() {
    ItemModel test = new ItemModel("test", "test", "test", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, test);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertNotEquals(201, orderModel.totalPrice());
  }

  @Test
  public void testValidGetOrderNumber() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals("O1", orderModel.getOrderId());
  }

  @Test
  public void testInvalidGetOrderNumber() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertNotEquals("O2", orderModel.getOrderId());
  }

  @Test
  public void testValidGetTimeOfOrder() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals(LocalDateTime.class, orderModel.getTimeOfOrder().getClass());

  }
}
