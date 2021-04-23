package com.app.controllers.menuactions;

import com.app.controllers.OrderController;

public class CreateOrderMenuAction extends MenuActions {

  public CreateOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new OrderController().createOrder();
  }
}
