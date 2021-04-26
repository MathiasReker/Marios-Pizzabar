package com.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModelTest {

  @Test
  public void testGetValidOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);

    Assertions.assertEquals(OrderStatusKeys.ACTIVE, orderModel.getOrderStatus());
  }

  @Test
  public void testGetInValidOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);

    Assertions.assertNotEquals(OrderStatusKeys.COMPLETE, orderModel.getOrderStatus());
  }

  @Test
  public void testGetValidTotalPrice() {
    ItemModel test = new ItemModel("Test", "Test", "Test", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(test, 2);

    Assertions.assertEquals(200, orderModel.totalPrice());
  }

  @Test
  public void testGetInvalidTotalPrice() {
    ItemModel test = new ItemModel("Test", "Test", "Test", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(test, 2);

    Assertions.assertNotEquals(201, orderModel.totalPrice());
  }

  @Test
  public void testValidGetOrderNumber() {
    ItemModel test = new ItemModel("Test", "Test", "Test", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(test, 2);

    Assertions.assertEquals(1, orderModel.getOrderId());
  }

  @Test
  public void testInvalidGetOrderNumber() {
    ItemModel test = new ItemModel("Test", "Test", "Test", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(test, 2);

    Assertions.assertNotEquals(2, orderModel.getOrderId());
  }

  @Test
  public void testValidGetTimeOfOrder() {
    ItemModel test = new ItemModel("Test", "Test", "Test", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(test, 2);

    Assertions.assertEquals(LocalDateTime.class, orderModel.getTimeOfOrder().getClass());
  }

  @Test
  public void testValidGetOrderlines() {
    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(itemModel, 2);

    Assertions.assertEquals(orderLineModels.get(0).getItem().getId(), orderModel.getOrderLines().get(0).getItem().getId());
  }

  @Test
  public void testInvalidGetOrderlines() {
    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(itemModel, 2);

    Assertions.assertNotEquals(orderLineModels, orderModel.getOrderLines());
  }

  @Test
  public void testValidSetOrderStatus() {
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.setOrderStatus(OrderStatusKeys.COMPLETE);

    Assertions.assertEquals(OrderStatusKeys.COMPLETE, orderModel.getOrderStatus());
  }

  @Test
  public void testInValidSetOrderStatus() {
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.setOrderStatus(OrderStatusKeys.COMPLETE);

    Assertions.assertNotEquals(OrderStatusKeys.ACTIVE, orderModel.getOrderStatus());
  }
}
