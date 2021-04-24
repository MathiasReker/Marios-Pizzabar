package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.StatisticsModel;
import com.app.models.services.ItemService;
import com.app.models.services.OrderService;
import com.app.views.StatisticsView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private StatisticsModel statisticsModel;
  private ArrayList<OrderModel> orderModels;

  {
    try {
      OrderService orderService = new OrderService();
      statisticsModel = new StatisticsModel();
      orderModels = orderService.getOrdersFromFile();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.print(e.getMessage());
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

    salesPerItem();
  }

  private String[] menuItems() {
    try {
      ItemModel[] readItems = ((new ItemService("data/itemdb/items.txt").getItemsFromFile())); // TODO softcode path
      String[] result = new String[readItems.length];

      for (int i = 0; i < result.length; i++) {
        result[i] = readItems[i].getItemName();
      }

      return result;
    } catch (FileNotFoundException e) {
      return null;
    }
  }

  private void salesPerItem() {
    String[] pizzaArray = menuItems();

    int[] index = new int[pizzaArray.length];

    for (OrderModel order : orderModels) {
      ArrayList<OrderLineModel> orderLineModels = order.getOrderLines();

      for (int i = 0; i < orderLineModels.size(); i++) {
        String pizzaName = orderLineModels.get(i).getItem().getItemName();
        int unit = orderLineModels.get(i).getQty();

        if (pizzaName.equals(pizzaArray[i])) {
          index[i] += unit;
        }
      }
    }

    STATISTIC_VIEW.print();
    STATISTIC_VIEW.print("Sale per item:");
    for (int i = 0; i < pizzaArray.length; i++) {
      STATISTIC_VIEW.print(pizzaArray[i] + ": " + index[i]);
    }
  }
}
