package com.app.controllers.menuactions;

import com.app.controllers.OrderController;

public class ViewActiveOrderMenuAction extends MenuAction {

  public ViewActiveOrderMenuAction(String description) {
    super(description);
  }

  @Override
  public void run() {
    new OrderController().viewOrders();
  }
}
