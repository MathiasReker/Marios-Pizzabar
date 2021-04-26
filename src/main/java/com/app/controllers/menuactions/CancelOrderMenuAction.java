package com.app.controllers.menuactions;

import com.app.controllers.OrderController;
import com.app.models.OrderStatusKeys;

public class CancelOrderMenuAction extends MenuActions {

  public CancelOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    OrderController orderController = new OrderController();
    orderController.viewActiveOrders();
    orderController.changeOrderStatus(OrderStatusKeys.CANCELLED);
  }
}
