package com.app.models.menuactions;

import com.app.controllers.OrderController;

public class ViewActiveOrderMenuAction extends MenuAction {

  public ViewActiveOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new OrderController().viewOrders();
    System.out.println("TODO: View active orders");
  }
}
