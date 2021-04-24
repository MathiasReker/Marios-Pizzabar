package com.app.controllers;

import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.StatisticsModel;
import com.app.models.services.OrderService;
import com.app.views.StatisticsView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private StatisticsModel statisticsModel;
  private OrderService orderService;
  private ArrayList<OrderModel> orderModels;

  {
    try {
      orderService = new OrderService();
    } catch (FileNotFoundException e) {
      // TODO: ORDER_VIEW.printInline("File does not exists.");
    }
  }

  {
    try {
      statisticsModel = new StatisticsModel();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print(e.getMessage());
    }
  }

  {
    try {
      orderModels = orderService.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      // TODO: ORDER_VIEW.printInline("File does not exists.");
    }
  }

  public void viewStatistics() {
    STATISTIC_VIEW.print("Orders today: " + statisticsModel.countOrdersToday());
    STATISTIC_VIEW.printInline("Average orders per hour: ");
    STATISTIC_VIEW.print(statisticsModel.salesPerHour());
    try {
      STATISTIC_VIEW.printInline("Average orders per day: ");
      STATISTIC_VIEW.print(statisticsModel.totalSalesPerDay());
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print(e.getMessage());
    }

    String[] pizzaArray = {"Margherita", "Kent Special"}; // TODO: Point to an array
    int[] amount = new int[pizzaArray.length]; // TODO: rename

    for (OrderModel order : orderModels) {
      ArrayList<OrderLineModel> orderLineModels = order.getOrderLines();

      for (int i = 0; i < orderLineModels.size(); i++) {
        String pizzaName = orderLineModels.get(i).getItem().getItemName();
        int unit = orderLineModels.get(i).getQty();

        if (pizzaName.equals(pizzaArray[i])) {
          amount[i] += unit;
        }
      }
    }

    STATISTIC_VIEW.print();
    STATISTIC_VIEW.print("Sale per item:");
    for (int i = 0; i < pizzaArray.length; i++) {
      STATISTIC_VIEW.print(pizzaArray[i] + ": " + amount[i]);
    }
  }
}
