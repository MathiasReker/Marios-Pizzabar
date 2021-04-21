package com.app;

import com.app.controller.MenuController;
import com.app.model.OrderLineModel;
import com.app.model.OrderModel;
import com.app.model.menuActions.*;

public class App {
  public static void main(String[] args) {
    createOrderMenuAction test2 = new createOrderMenuAction();
    exitMenuAction test3 = new exitMenuAction();
    MenuAction[] menu = {test2,test3};
    MenuController test = new MenuController("Hello", "Hello", menu);
    test.run();

    OrderLineModel orderLineModel1 = new OrderLineModel(1, "1", 30);
    OrderLineModel orderLineModel2 = new OrderLineModel(2, "2", 30);

    OrderModel orderModel = new OrderModel("1", 0);
    orderModel.addOrderLine(orderLineModel1);
    orderModel.addOrderLine(orderLineModel2);

    System.out.println(orderModel.orderToString());

  }
}
