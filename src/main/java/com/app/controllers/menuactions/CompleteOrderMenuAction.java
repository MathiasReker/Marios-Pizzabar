package com.app.controllers.menuactions;

import com.app.controllers.OrderController;
import com.app.models.OrderStatusKeys;

public class CompleteOrderMenuAction extends MenuActions {

  public CompleteOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new OrderController().changeOrderStatus(OrderStatusKeys.COMPLETE);
  }
}
