package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderModel;
import com.app.models.OrderStatusKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class OrderControllerTest {

  @Test
  public void testValidLookUpOrder() {

    OrderController orderController = new OrderController();

    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(itemModel, 2);
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    orderModels.add(orderModel);

    OrderModel expected = orderModel;

    Assertions.assertEquals(expected, orderController.lookupOrder(1, orderModels));
  }

  @Test
  public void testInvalidLookUpOrder() {

    OrderController orderController = new OrderController();

    ItemModel itemModel = new ItemModel("1", "Pizza", "Tomat, Ost, Skinke", 100);
    OrderModel orderModel = new OrderModel(1, OrderStatusKeys.ACTIVE);
    orderModel.addOrderLine(itemModel, 2);
    ArrayList<OrderModel> orderModels = new ArrayList<>();
    orderModels.add(orderModel);


    Assertions.assertThrows(IllegalArgumentException.class, () -> orderController.lookupOrder(2, orderModels));
  }
}
