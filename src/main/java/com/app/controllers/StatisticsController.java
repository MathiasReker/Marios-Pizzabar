package com.app.controllers;

import com.app.models.OrderModel;
import com.app.models.StatisticsModel;
import com.app.views.StatisticsView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class StatisticsController {
  private final StatisticsView STATISTIC_VIEW = new StatisticsView();
  private StatisticsModel statisticsModel;

  public StatisticsController() {
    try {
      statisticsModel = new StatisticsModel();
    } catch (FileNotFoundException e) {
      STATISTIC_VIEW.printInline("File does not exists.");
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

    String[] menuItemNames = statisticsModel.menuItems();
    int[] salesAmount = statisticsModel.salesPerItemPerDay();

    STATISTIC_VIEW.print();
    STATISTIC_VIEW.print("Sale per item:");
    for (int k = 0; k < menuItemNames.length; k++) {
      STATISTIC_VIEW.print(menuItemNames[k] + ": " + salesAmount[k]); // TODO: Beautify
    }
  }
}
