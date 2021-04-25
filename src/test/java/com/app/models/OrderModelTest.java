package com.app.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderModelTest {

  @Test
  public void testGetValidOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertEquals(OrderStatusKeys.ACTIVE, orderModel.getOrderStatus());
  }

  @Test
  public void testGetInValidOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertNotEquals(OrderStatusKeys.COMPLETE, orderModel.getOrderStatus());
  }

  @Test
  public void testGetValidTotalPrice() {
    ItemModel test = new ItemModel("test", "test", "test", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, test);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertEquals(200, orderModel.totalPrice());
  }

  @Test
  public void testGetInvalidTotalPrice() {
    ItemModel test = new ItemModel("test", "test", "test", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, test);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertNotEquals(201, orderModel.totalPrice());
  }

  @Test
  public void testValidGetOrderNumber() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertEquals(1, orderModel.getOrderId());
  }

  @Test
  public void testInvalidGetOrderNumber() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertNotEquals(2, orderModel.getOrderId());
  }

  @Test
  public void testValidGetTimeOfOrder() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertEquals(LocalDateTime.class, orderModel.getTimeOfOrder().getClass());
  }

  @Test
  public void testValidGetOrderlines() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertEquals(orderLineModels, orderModel.getOrderLines());
  }

  @Test
  public void testInValidGetOrderlines() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    OrderLineModel orderLineModel1 = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    ArrayList<OrderLineModel> orderLineModels2 = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    orderLineModels2.add(orderLineModel1);

    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);

    Assertions.assertNotEquals(orderLineModels2, orderModel.getOrderLines());
  }

  @Test
  public void testValidSetOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);
    orderModel.setOrderStatus(OrderStatusKeys.COMPLETE);

    Assertions.assertEquals(OrderStatusKeys.COMPLETE, orderModel.getOrderStatus());
  }

  @Test
  public void testInValidSetOrderStatus() {
    ItemModel itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);
    orderModel.setOrderStatus(OrderStatusKeys.COMPLETE);

    Assertions.assertNotEquals(OrderStatusKeys.ACTIVE, orderModel.getOrderStatus());
  }
}
