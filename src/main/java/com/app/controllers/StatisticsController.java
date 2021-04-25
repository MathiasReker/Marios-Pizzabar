package com.app.controllers;

import com.app.models.ItemModel;
import com.app.models.OrderLineModel;
import com.app.models.OrderModel;
import com.app.models.StatisticsModel;
import com.app.models.services.ConfigService;
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
      String path = new ConfigService("itemDb").getPath();
      ItemModel[] readItems = ((new ItemService(path).getItemsFromFile()));
      String[] result = new String[readItems.length];

      for (int i = 0; i < readItems.length; i++) {
        result[i] = readItems[i].getItemName();
      }

      return result;
    } catch (FileNotFoundException e) {

      System.out.println(e.getMessage());
    }
    return null;
  }

  private void salesPerItem() {
    String[] menuItems = menuItems();
    int[] units = new int[menuItems.length];
    for (int menuItemIndex = 0; menuItemIndex < menuItems.length; menuItemIndex++) {
      for (OrderModel order : orderModels) {
        ArrayList<OrderLineModel> orderLineModels = order.getOrderLines();
        for (OrderLineModel orderLineModel : orderLineModels) {
          String pizzaName = orderLineModel.getItem().getItemName();
          int qty = orderLineModel.getQty();
          if (pizzaName.equals(menuItems[menuItemIndex])) {
            units[menuItemIndex] += qty;
          }
        }
      }
    }

    STATISTIC_VIEW.print();
    STATISTIC_VIEW.print("Sale per item:");
    for (int k = 0; k < menuItems.length; k++) {
      STATISTIC_VIEW.print(menuItems[k] + ": " + units[k]);
    }
  }
}
