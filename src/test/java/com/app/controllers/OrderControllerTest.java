package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.OrderStatusKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;



public class OrderControllerTest {

  @Test
  public void testValidLookUpOrder(){

    OrderController orderController = new OrderController();

    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    orderModels.add(orderModel);

    OrderModel expected = orderModel;

    Assertions.assertEquals(expected, orderController.lookupOrder(1,orderModels));
  }

  @Test
  public void testInvalidLookUpOrder(){

    OrderController orderController = new OrderController();

    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderLineModel orderLineModel = new OrderLineModel(2, itemModel);
    ArrayList<OrderLineModel> orderLineModels = new ArrayList<>();
    orderLineModels.add(orderLineModel);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE, orderLineModels);
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    orderModels.add(orderModel);

    OrderModel expected = orderModel;

    Assertions.assertNotEquals(expected, orderController.lookupOrder(2,orderModels));
  }
}
