package com.app.models;

import com.app.controllers.ItemController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTest {

  ItemModel itemModel;

  @BeforeEach
  public void setUp(){
    itemModel = new ItemModel("1", "pizza1", "alt muligt lækkert", 100);
  }


  @Test
   void getValidOrderStatus() {
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals(0,orderModel.getOrderStatus());
  }

  @Test
  void getInValidOrderStatus() {
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertNotEquals(1,orderModel.getOrderStatus());
  }

  @Test
  void testGetValidTotalPrice() {
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals(200, orderModel.totalPrice());
  }
  @Test
  void testGetInvalidTotalPrice() {
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertNotEquals(201, orderModel.totalPrice());
  }

  @Test
  void testValidGetOrderNumber(){
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals("O1", orderModel.getOrderId());
  }

  @Test
  void testInvalidGetOrderNumber(){
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals("O2", orderModel.getOrderId());
  }

  @Test
  void testValidGetTimeOfOrder(){
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel("O1", 0, orderLineModels);

    assertEquals(LocalDateTime.class, orderModel.getTimeOfOrder().getClass());

  }
}
