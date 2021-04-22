package com.app.model.menuactions;

import com.app.controller.OrderController;

public class CreateOrderMenuAction extends MenuAction {

  public CreateOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new OrderController().createOrder();
  }
}
