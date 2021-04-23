package com.app.controllers.menuactions;

import com.app.controllers.OrderController;

public class CompleteOrderMenuAction extends MenuActions{

  public CompleteOrderMenuAction(String description){
    super(description);
  }

  @Override
  public void run() {
    new OrderController().changeOrderStatus(1);
  }
}
