package com.app.controllers.menuactions;

import com.app.controllers.OrderController;

public class CancelOrderMenuAction extends MenuActions {

  public CancelOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    OrderController orderController = new OrderController();
    orderController.viewActiveOrders();
    orderController.changeOrderStatus(2);
  }
}

